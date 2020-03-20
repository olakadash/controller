package com.ola.utils;

public enum Constants {

    PASSWORD("password"), STUDENT_ID("studentId"),FIRST_NAME("firstName"),LAST_NAME("lastName"),
    EMAIL("email"),USER_TYPE("userType"),USER_ID("userId"),INSTRUCTOR("instructor"),
    COURSE_CODE("courseCode"),CAPACITY("capacity"),STARTING_DATE("startingDate")
    ,DURATION("duration"),HOURS("hours"),COURSE_ID("courseId"),
    COURSE_Name("courseName");

    private String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
