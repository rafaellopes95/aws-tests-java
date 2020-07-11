package my.lambda.app.handler;

import io.micronaut.function.executor.FunctionInitializer;
import my.lambda.app.service.DynamoDBService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SampleHandler extends FunctionInitializer {

    @Inject
    private DynamoDBService service;

    public void executeGetRequest() {
        service.getItem();
    }

    public void executeInsertRequest() {
        service.insertItem();
    }

    private void executeUpdateRequest() {
        service.updateItem();
    }

    public static void main(String[] args) {
        SampleHandler handler = new SampleHandler();
        //handler.executeInsertRequest();
        handler.executeUpdateRequest();
        handler.executeGetRequest();
    }
}
