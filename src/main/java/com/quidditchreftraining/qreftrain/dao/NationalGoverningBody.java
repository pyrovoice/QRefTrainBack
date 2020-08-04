package com.quidditchreftraining.qreftrain.dao;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "national_governing_body")
public class NationalGoverningBody {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String abbreviation;
    private String location;
}
