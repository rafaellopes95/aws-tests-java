package my.lambda.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import my.lambda.app.model.MessageDTO;
import org.apache.commons.collections4.ListUtils;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kinesis.KinesisClient;
import software.amazon.awssdk.services.kinesis.model.PutRecordsRequest;
import software.amazon.awssdk.services.kinesis.model.PutRecordsRequestEntry;
import software.amazon.awssdk.services.kinesis.model.PutRecordsResponse;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class KinesisService {

    @Inject
    private MessageProducerService messageService;

    private static final String KINESIS_STREAM_NAME = "myTestKinesisStream";
    private static final String KINESIS_PARTITION_KEY = "MessageDTOType";
    private ObjectMapper mapper = new ObjectMapper();
    private KinesisClient kinesisClient = KinesisClient.builder().
            region(Region.US_EAST_2)
            .build();

    public void putMessagesIntoStream(List<MessageDTO> dtoList) {

        List<PutRecordsRequestEntry> messageBatch = dtoList.stream()
                .map(dto -> {
                    try {
                        return mapper.writeValueAsString(dto);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .map(dtoJson -> PutRecordsRequestEntry.builder()
                        .data(SdkBytes.fromUtf8String(dtoJson))
                        .partitionKey(KINESIS_PARTITION_KEY)
                        .build()
                )
                .collect(Collectors.toList());

        List<List<PutRecordsRequestEntry>> partitionedMessageBatch = ListUtils.partition(messageBatch, 500);

        for(List<PutRecordsRequestEntry> messageBatchPartition : partitionedMessageBatch) {
            PutRecordsRequest recordsRequest = PutRecordsRequest.builder()
                    .streamName(KINESIS_STREAM_NAME)
                    .records(messageBatchPartition)
                    .build();

            PutRecordsResponse putRecordsResponse = kinesisClient.putRecords(recordsRequest);

            log.info("Kinesis response: {}", putRecordsResponse.sdkHttpResponse().toString());
        }
    }
}
