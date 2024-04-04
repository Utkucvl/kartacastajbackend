package com.example.kartaca.dto;
import lombok.Data;

@Data
public class EarthquakeDto {
    private String id;
    private double latitude;
    private double longitude;
    private double magnitude;
}
