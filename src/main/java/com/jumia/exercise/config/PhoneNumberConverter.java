package com.jumia.exercise.config;

import com.jumia.exercise.vo.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.jumia.exercise.model.Country.fromCountryCode;
import static java.lang.String.format;
import static java.util.regex.Pattern.compile;

@Slf4j
@Converter
public class PhoneNumberConverter implements AttributeConverter<PhoneNumber, String> {

    public static final String REGEX = "\\(\\d{3}\\) ?\\d+$";
    private static final String FORMATED_NUMBER_MASK = "(%s) %s";

    @Override
    public String convertToDatabaseColumn(PhoneNumber attribute) {
        if (attribute != null) {
            return format(FORMATED_NUMBER_MASK, attribute.getCountryCode(), attribute.getNumber());
        }

        return "";
    }

    @Override
    public PhoneNumber convertToEntityAttribute(String dbData) {
        if (dbData != null && !dbData.isEmpty() && compile(REGEX).matcher(dbData).matches()) {
            var countryCode = dbData.substring(1, 4);
            var country = fromCountryCode(countryCode);
            return PhoneNumber.builder()
                    .country(country)
                    .countryCode(countryCode)
                    .number(dbData.substring(6))
                    .status(country.isValid(dbData))
                    .build();
        }

        return PhoneNumber.builder().build();
    }
}
