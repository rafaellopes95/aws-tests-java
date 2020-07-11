//package my.lambda.app.repository.datasource;
//
//import com.amazonaws.auth.AWSCredentialsProvider;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
//import com.amazonaws.services.dynamodbv2.document.DynamoDB;
//import lombok.Getter;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.inject.Singleton;
//
//@Slf4j
//@Singleton
//public class DynamoDBDataSource {
//
//    private AmazonDynamoDB dataSource = AmazonDynamoDBClientBuilder.standard()
//            .withRegion("us-east-2")
//            .build();
//    private DynamoDB dynamoDB = new DynamoDB(dataSource);
//
//
//
//    public DynamoDB getDynamoDB() {
//        return dynamoDB;
//    }
//}
