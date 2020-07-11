package my.lambda.app.repository;

import io.micronaut.context.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Singleton
public class DynamoDBRepository {

    @Value("${dynamodb.table.name}")
    private String tableName;

    private DynamoDbClient dynamoDbClient = DynamoDbClient.builder().region(Region.US_EAST_2).build();

    public void insertItem() {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("Id", AttributeValue.builder().s("4").build());
        item.put("Department", AttributeValue.builder().s("HR").build());
        item.put("Name", AttributeValue.builder().s("Ryan").build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();

        PutItemResponse putItemResponse = dynamoDbClient.putItem(request);

        log.info("Put Item status code: {}", putItemResponse.toString());
    }

    public String getItem() {
        Map<String, AttributeValue> itemKey = new HashMap<>();
        itemKey.put("Id", AttributeValue.builder().s("4").build());
        itemKey.put("Department", AttributeValue.builder().s("HR").build());

        GetItemRequest request = GetItemRequest.builder()
                .tableName(tableName)
                .key(itemKey)
                .attributesToGet("Name")
                .build();

        Map<String, AttributeValue> item = dynamoDbClient.getItem(request).item();

        log.info("The employee name is: {}", item.get("Name").s());

        return null;
    }

    public String updateItem() {
        Map<String, AttributeValue> itemKey = new HashMap<>();
        itemKey.put("Id", AttributeValue.builder().s("4").build());
        itemKey.put("Department", AttributeValue.builder().s("HR").build());

        Map<String, AttributeValueUpdate> nameChange = new HashMap<>();
        nameChange.put("Name", AttributeValueUpdate.builder().value(AttributeValue.builder().s("Ryan Jon").build()).build());

        UpdateItemRequest request = UpdateItemRequest.builder()
                .tableName(tableName)
                .key(itemKey)
                .attributeUpdates(nameChange)
                .build();

        UpdateItemResponse updateItemResponse = dynamoDbClient.updateItem(request);

        log.info("Update status code: {}", updateItemResponse.sdkHttpResponse().statusCode());

        return null;
    }
}
