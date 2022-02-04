package com.jumia.exercise.model;

import com.jumia.exercise.config.PhoneNumberConverter;
import com.jumia.exercise.vo.PhoneNumber;
import lombok.Data;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer id;
    private String name;

    @Convert(converter = PhoneNumberConverter.class)
    private PhoneNumber phone;
}
