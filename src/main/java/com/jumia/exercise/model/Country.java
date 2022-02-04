package com.jumia.exercise.model;

import lombok.Getter;

import static com.jumia.exercise.model.PhoneState.INVALID;
import static com.jumia.exercise.model.PhoneState.VALID;
import static java.util.Arrays.stream;
import static java.util.Optional.ofNullable;
import static java.util.regex.Pattern.compile;

public enum Country {
    NONE("", ""),
    UGANDA("256", "\\(256\\) ?\\d{9}$"),
    MOROCCO("212", "\\(212\\) ?[5-9]\\d{8}$"),
    ETHIOPIA("251", "\\(251\\) ?[1-59]\\d{8}$"),
    MOZAMBIQUE("258", "\\(258\\) ?[28]\\d{7,8}$"),
    CAMEROON("237", "\\(237\\) ?[2368]\\d{7,8}$");

    @Getter
    private final String pattern;
    private final String countryCode;

    Country(String countryCode, String pattern) {
        this.countryCode = countryCode;
        this.pattern = pattern;
    }

    public static Country fromCountryCode(String countryCode) {
        return stream(values())
                .filter(country -> country.countryCode.equals(countryCode))
                .findFirst()
                .orElse(NONE);
    }

    public PhoneState isValid(String phoneNumber) {
        return compile(this.getPattern())
                .matcher(ofNullable(phoneNumber).orElse(""))
                .matches() ? VALID : INVALID;
    }
}
