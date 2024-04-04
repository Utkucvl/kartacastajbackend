package com.example.kartaca.controller;
import com.example.kartaca.dto.EarthquakeDto;
import com.example.kartaca.service.impl.EarthquakeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/earthquake")
public class EarthquakeController {
    @Autowired
    private EarthquakeServiceImpl earthquakeServiceImpl;

    @GetMapping
    public ResponseEntity<List<EarthquakeDto>> getAll(
            @RequestParam("startDate") String startDateStr,
            @RequestParam("finishDate") String finishDateStr,
            @RequestParam("magnitude") double magnitude) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = dateFormat.parse(startDateStr);
            Date finishDate = dateFormat.parse(finishDateStr);

            List<EarthquakeDto> data = earthquakeServiceImpl.getEarthquakesWithDateAndMagnitude(startDate, finishDate, magnitude);
            return ResponseEntity.ok(data);
        } catch (ParseException e) {
            // Handle parsing exception
            return ResponseEntity.badRequest().build(); // Return bad request response
        }
    }
}
