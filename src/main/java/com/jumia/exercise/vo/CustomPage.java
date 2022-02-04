package com.jumia.exercise.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class CustomPage {

    private Integer page;
    private Integer size;
    private Collection<PhoneNumber> content;
}
