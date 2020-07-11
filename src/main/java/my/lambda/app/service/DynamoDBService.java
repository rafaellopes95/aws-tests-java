package my.lambda.app.service;

import lombok.extern.slf4j.Slf4j;
import my.lambda.app.repository.DynamoDBRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Slf4j
@Singleton
public class DynamoDBService {

    @Inject
    private DynamoDBRepository repository;

    public void getItem() { repository.getItem(); }

    public void insertItem() {
        repository.insertItem();
    }

    public void updateItem() {repository.updateItem();  }
}
