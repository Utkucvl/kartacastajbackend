package com.example.kartaca.dao.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "earthquakes")
@Data
public class Earthquake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "magnitude")
    private double magnitude;

    @Column(name = "place")
    private String place;

    @Column(name = "time")
    private long time;

    @Column(name = "updated")
    private long updated;

    @Column(name = "url")
    private String url;

    @Column(name = "detail")
    private String detail;

    @Column(name = "status")
    private String status;

    @Column(name = "tsunami")
    private int tsunami;

    @Column(name = "sig")
    private int sig;

    @Column(name = "net")
    private String net;

    @Column(name = "code")
    private String code;

    @Column(name = "ids")
    private String ids;

    @Column(name = "sources")
    private String sources;

    @Column(name = "types")
    private String types;

    @Column(name = "nst")
    private int nst;

    @Column(name = "dmin")
    private double dmin;

    @Column(name = "rms")
    private double rms;

    @Column(name = "gap")
    private int gap;

    @Column(name = "mag_type")
    private String magType;

    @Column(name = "title")
    private String title;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "depth")
    private double depth;
}
