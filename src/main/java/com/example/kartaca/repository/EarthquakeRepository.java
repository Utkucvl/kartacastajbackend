package com.example.kartaca.repository;

import com.example.kartaca.dao.entity.Earthquake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EarthquakeRepository extends JpaRepository<Earthquake,Long> {

}
