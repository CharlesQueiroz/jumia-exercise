package com.jumia.exercise.vo;

import com.jumia.exercise.model.Country;
import com.jumia.exercise.model.PhoneState;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class PhoneNumber {

    @NotNull
    private String number;
    private Country country;
    private PhoneState state;

    @NotNull
    private String countryCode;

}
