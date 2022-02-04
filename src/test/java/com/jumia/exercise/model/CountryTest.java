package com.jumia.exercise.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.jumia.exercise.model.Country.*;
import static com.jumia.exercise.model.PhoneStatus.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CountryTest {

    @Test
    void should_return_uganda_with_a_valid_param() {
        assertEquals(UGANDA, fromCountryCode("256"));
    }

    @Test
    void should_return_morocco_with_a_valid_param() {
        assertEquals(MOROCCO, fromCountryCode("212"));
    }
    @Test
    void should_return_ethiopia_with_a_valid_param() {
        assertEquals(ETHIOPIA, fromCountryCode("251"));
    }
    @Test
    void should_return_mozambique_with_a_valid_param() {
        assertEquals(MOZAMBIQUE, fromCountryCode("258"));
    }
    @Test
    void should_return_cameroon_with_a_valid_param() {
        assertEquals(CAMEROON, fromCountryCode("237"));
    }
    @Test
    void should_return_none_with_a_invalid_param() {
        assertEquals(NONE, fromCountryCode("444444"));
    }

    @Test
    void should_return_none_with_a_empty_param() {
        assertEquals(NONE, fromCountryCode(""));
    }

    @Test
    void should_return_none_with_a_null_param() {
        assertEquals(NONE, fromCountryCode(null));
    }

    @Test
    void should_return_valid_for_uganda_validation() {
        assertEquals(VALID, UGANDA.isValid("(256) 714660221"));
    }

    @Test
    void should_return_invalid_for_uganda_validation() {
        assertEquals(INVALID, UGANDA.isValid("(212) 654642448"));
    }

    @Test
    void should_return_invalid_for_uganda_validation_with_invalid_param() {
        assertEquals(INVALID, UGANDA.isValid("XXXXXXX"));
    }

    @Test
    void should_return_invalid_for_uganda_validation_with_empty_param() {
        assertEquals(INVALID, UGANDA.isValid(""));
    }

    @Test
    void should_return_invalid_for_uganda_validation_with_null_param() {
        assertEquals(INVALID, UGANDA.isValid(null));
    }

    @Test
    void should_return_valid_for_morocco_validation() {
        assertEquals(VALID, MOROCCO.isValid("(212) 654642448"));
    }

    @Test
    void should_return_invalid_for_morocco_validation() {
        assertEquals(INVALID, MOROCCO.isValid("(256) 714660221"));
    }

    @Test
    void should_return_invalid_for_morocco_validation_with_invalid_param() {
        assertEquals(INVALID, MOROCCO.isValid("XXXXXXX"));
    }

    @Test
    void should_return_invalid_for_morocco_validation_with_empty_param() {
        assertEquals(INVALID, MOROCCO.isValid(""));
    }

    @Test
    void should_return_invalid_for_morocco_validation_with_null_param() {
        assertEquals(INVALID, MOROCCO.isValid(null));
    }

    @Test
    void should_return_valid_for_ethiopia_validation() {
        assertEquals(VALID, ETHIOPIA.isValid("(251) 914701723"));
    }

    @Test
    void should_return_invalid_for_ethiopia_validation() {
        assertEquals(INVALID, ETHIOPIA.isValid("(258) 848826725"));
    }

    @Test
    void should_return_invalid_for_ethiopia_validation_with_invalid_param() {
        assertEquals(INVALID, ETHIOPIA.isValid("XXXXXXX"));
    }

    @Test
    void should_return_invalid_for_ethiopia_validation_with_empty_param() {
        assertEquals(INVALID, ETHIOPIA.isValid(""));
    }

    @Test
    void should_return_invalid_for_ethiopia_validation_with_null_param() {
        assertEquals(INVALID, ETHIOPIA.isValid(null));
    }

    @Test
    void should_return_valid_for_mozambique_validation() {
        assertEquals(VALID, MOZAMBIQUE.isValid("(258) 848826725"));
    }

    @Test
    void should_return_invalid_for_mozambique_validation() {
        assertEquals(INVALID, MOZAMBIQUE.isValid("(111) 848826725"));
    }

    @Test
    void should_return_invalid_for_mozambique_validation_with_invalid_param() {
        assertEquals(INVALID, MOZAMBIQUE.isValid("XXXXXXX"));
    }

    @Test
    void should_return_invalid_for_mozambique_validation_with_empty_param() {
        assertEquals(INVALID, MOZAMBIQUE.isValid(""));
    }

    @Test
    void should_return_invalid_for_mozambique_validation_with_null_param() {
        assertEquals(INVALID, MOZAMBIQUE.isValid(null));
    }

    @Test
    void should_return_valid_for_cameroon_validation() {
        assertEquals(VALID, CAMEROON.isValid("(237) 696443597"));
    }

    @Test
    void should_return_invalid_for_cameroon_validation() {
        assertEquals(INVALID, CAMEROON.isValid("(333) 848826725"));
    }

    @Test
    void should_return_invalid_for_cameroon_validation_with_invalid_param() {
        assertEquals(INVALID, CAMEROON.isValid("XXXXXXX"));
    }

    @Test
    void should_return_invalid_for_cameroon_validation_with_empty_param() {
        assertEquals(INVALID, CAMEROON.isValid(""));
    }

    @Test
    void should_return_invalid_for_cameroon_validation_with_null_param() {
        assertEquals(INVALID, CAMEROON.isValid(null));
    }

}
