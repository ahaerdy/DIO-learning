package br.com.dio.model;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private int code;
    private String userName;
    private LocalDate birthday;
}