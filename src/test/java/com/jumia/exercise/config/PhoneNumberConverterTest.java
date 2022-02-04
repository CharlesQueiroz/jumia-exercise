package com.jumia.exercise.config;

import com.jumia.exercise.vo.PhoneNumber;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.jumia.exercise.model.Country.MOROCCO;
import static com.jumia.exercise.model.Country.MOZAMBIQUE;
import static com.jumia.exercise.model.PhoneStatus.VALID;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PhoneNumberConverterTest {

    @Test
    void should_convert_phone_number_to_string() {
        var phoneNumber = PhoneNumber.builder()
                .countryCode("+212")
                .country(MOROCCO)
                .number("6007989253")
                .build();
        assertEquals("(+212) 6007989253", new PhoneNumberConverter().convertToDatabaseColumn(phoneNumber));
    }

    @Test
    void should_return_empty_string_on_null_phonenumber() {
        assertEquals("", new PhoneNumberConverter().convertToDatabaseColumn(null));
    }

    @Test
    void should_convert_string_to_phone_number() {
        var phoneNumber = PhoneNumber.builder()
                .countryCode("258")
                .country(MOZAMBIQUE)
                .number("823747618")
                .status(VALID)
                .build();
        assertEquals(phoneNumber, new PhoneNumberConverter().convertToEntityAttribute("(258) 823747618"));
    }

    @Test
    void should_return_empty_with_null_param() {
        assertEquals(PhoneNumber.builder().build(), new PhoneNumberConverter().convertToEntityAttribute(null));
    }

    @Test
    void should_return_empty_with_empty_param() {
        assertEquals(PhoneNumber.builder().build(), new PhoneNumberConverter().convertToEntityAttribute(""));
    }

    @Test
    void should_return_empty_with_invalid_param() {
        assertEquals(PhoneNumber.builder().build(), new PhoneNumberConverter().convertToEntityAttribute("(333333) 233"));
    }
}
