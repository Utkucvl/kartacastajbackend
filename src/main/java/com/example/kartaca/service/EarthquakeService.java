package com.example.kartaca.service;

import com.example.kartaca.dto.EarthquakeDto;

import java.util.Date;
import java.util.List;

public interface EarthquakeService {
    List<EarthquakeDto> getEarthquakesWithDateAndMagnitude(Date startDate,Date finishDate , double magnitude);
}
