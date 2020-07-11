package my.lambda.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    private int id;
    private String name;
    private int age;
    private LocalDate birthDate;
    private char gender;
}
