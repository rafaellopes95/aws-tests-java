package my.lambda.app.service;

import lombok.extern.slf4j.Slf4j;
import my.lambda.app.model.MessageDTO;

import javax.inject.Singleton;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Singleton
@Slf4j
public class MessageProducerService {

    public List<MessageDTO> getThousandMessages() {
        List<MessageDTO> thousandMessages = new ArrayList<>();
        while(thousandMessages.size() < 1000) {
            thousandMessages.addAll(getMessages());
        }
        log.info("Message list size: {}", thousandMessages.size());
        return thousandMessages;
    }

    public List<MessageDTO> getMessages() {
        return Arrays.asList(
                new MessageDTO(1, "John", 20, LocalDate.of(2000, 1, 11), 'M'),
                new MessageDTO(2, "Jessie", 25, LocalDate.of(1995, 2, 12), 'F'),
                new MessageDTO(3, "Alex", 30, LocalDate.of(1990, 3, 13), 'M'),
                new MessageDTO(4, "Alexa", 35, LocalDate.of(1985, 4, 14), 'F'),
                new MessageDTO(5, "Mario", 40, LocalDate.of(1980, 5, 15), 'M'),
                new MessageDTO(6, "Maria", 45, LocalDate.of(1975, 6, 16), 'F'),
                new MessageDTO(7, "Bob", 50, LocalDate.of(1970, 7, 17), 'M'),
                new MessageDTO(8, "Betty", 55, LocalDate.of(1965, 8, 18), 'F'),
                new MessageDTO(9, "Ryan", 60, LocalDate.of(1960, 9, 19), 'M'),
                new MessageDTO(10, "Rachel", 65, LocalDate.of(1955, 10, 20), 'F')
        );
    }
}
