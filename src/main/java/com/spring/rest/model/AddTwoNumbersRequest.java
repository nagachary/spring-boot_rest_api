package com.spring.rest.model;

public class AddTwoNumbersRequest {

    private Integer number_1;
    private Integer number_2;

    public AddTwoNumbersRequest(Integer number_1, Integer number_2) {
        this.number_1 = number_1;
        this.number_2 = number_2;
    }

    public Integer getNumber_1() {
        return number_1;
    }

    public void setNumber_1(Integer number_1) {
        this.number_1 = number_1;
    }

    public Integer getNumber_2() {
        return number_2;
    }

    public void setNumber_2(Integer number_2) {
        this.number_2 = number_2;
    }
}
