package br.com.dio.model;

import br.com.dio.annotation.SerializedMethod;
import br.com.dio.annotation.SerializerType;

import static br.com.dio.annotation.FieldFormatEnum.PASCAL_CASE;

@SerializerType(fieldFormat = PASCAL_CASE, prettify = false)
public class Person {

    private long id;

    private String name;

    private int age;

    public Person() {
    }

    public Person(final long id, final String name, final int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @SerializedMethod("firstPersonName")
    public String firstName() {
        return name.split(" ")[0];
    }
}
