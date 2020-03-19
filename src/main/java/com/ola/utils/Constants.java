package com.ola.utils;

public enum Constants {

    PASSWORD("password"), STUDENT_ID("studentId"),FIRST_NAME("firstName"),LAST_NAME("lastName"),
    EMAIL("email");

    private String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
