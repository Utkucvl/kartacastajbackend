package com.example.kartaca.service.impl;

import com.example.kartaca.dto.EarthquakeDto;
import com.example.kartaca.repository.EarthquakeRepository;
import com.example.kartaca.service.EarthquakeService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class EarthquakeServiceImpl implements EarthquakeService {
    private final String USGS_API_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query";

    @Autowired
    private EarthquakeRepository earthquakeRepository;

    @Override
    public List<EarthquakeDto> getEarthquakesWithDateAndMagnitude(Date startDate, Date finishDate, double magnitude) {
        // USGS API'ye istek yaparak tarihe ve büyüklüğe göre filtrelenmiş depremleri al
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String startDateString = dateFormat.format(startDate);
        String finishDateString = dateFormat.format(finishDate);
        String apiUrl = USGS_API_URL + "?format=geojson&starttime=" + startDateString + "&endtime=" + finishDateString + "&minmagnitude=" + magnitude;

        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(apiUrl, String.class);

        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            // İstek başarısız olduysa boş liste döndür
            return Collections.emptyList();
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(responseEntity.getBody());
            JsonNode featuresNode = root.get("features");

            if (featuresNode == null || !featuresNode.isArray()) {
                return Collections.emptyList();
            }

            List<EarthquakeDto> earthquakeDtos = new ArrayList<>();
            for (JsonNode featureNode : featuresNode) {
                EarthquakeDto earthquakeDto = new EarthquakeDto();
                earthquakeDto.setId(featureNode.get("id").asText());
                earthquakeDto.setLatitude(featureNode.get("geometry").get("coordinates").get(1).asDouble());
                earthquakeDto.setLongitude(featureNode.get("geometry").get("coordinates").get(0).asDouble());
                earthquakeDto.setMagnitude(featureNode.get("properties").get("mag").asDouble());
                earthquakeDtos.add(earthquakeDto);
            }

            return earthquakeDtos;
        } catch (Exception e) {
            // JSON işleme hatası oluştuğunda veya dönüştürme hatası oluştuğunda
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
