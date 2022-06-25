package com.lding.mongo.povo;

import lombok.Data;

@Data
public class TravelVO {

    private int month;  //月份
    private double sum;
    private double avg;
    private double min;
    private double max;
}
