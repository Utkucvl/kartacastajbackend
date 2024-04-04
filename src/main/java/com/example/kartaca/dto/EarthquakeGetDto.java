package com.example.kartaca.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EarthquakeGetDto {

    private Date startDate;
    private Date finishDate;
    private double magnitude;
}
