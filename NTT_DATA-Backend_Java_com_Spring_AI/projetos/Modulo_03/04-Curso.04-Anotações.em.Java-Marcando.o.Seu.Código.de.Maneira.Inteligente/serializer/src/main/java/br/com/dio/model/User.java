package br.com.dio.model;

import br.com.dio.annotation.SerializerType;
import static br.com.dio.annotation.FieldFormatEnum.SNAKE_CASE;

@SerializerType(fieldFormat = SNAKE_CASE)

public record User(
        long id,
        String fullName,
        int age,
        double salary
) { }
