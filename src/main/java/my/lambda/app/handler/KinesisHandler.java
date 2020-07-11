package my.lambda.app.handler;

import io.micronaut.function.executor.FunctionInitializer;
import my.lambda.app.service.KinesisService;
import my.lambda.app.service.MessageProducerService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class KinesisHandler extends FunctionInitializer {

    @Inject
    private KinesisService kinesisService;

    @Inject
    private MessageProducerService messageService;

    public void publishMessagesToKinesis() {
        kinesisService.putMessagesIntoStream(messageService.getThousandMessages());
    }

    public static void main(String[] args) {
        KinesisHandler handler = new KinesisHandler();
        handler.publishMessagesToKinesis();
    }
}
