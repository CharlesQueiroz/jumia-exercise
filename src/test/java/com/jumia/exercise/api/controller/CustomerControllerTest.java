package com.jumia.exercise.api.controller;

import com.jumia.exercise.service.CustomerService;
import com.jumia.exercise.vo.CustomPage;
import com.jumia.exercise.vo.PhoneNumber;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

import static com.jumia.exercise.model.Country.*;
import static com.jumia.exercise.model.PhoneStatus.INVALID;
import static com.jumia.exercise.model.PhoneStatus.VALID;
import static io.restassured.RestAssured.get;
import static java.util.stream.Stream.of;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class CustomerControllerTest {

    @LocalServerPort
    private int port;

    private String uri;

    @MockBean
    private CustomerService customerService;

    @PostConstruct
    public void init() {
        uri = "http://localhost:" + port;
    }

    @Test
    void should_return_all_phone_numbers() {
        var phone01 = PhoneNumber.builder().number("6546545369").status(VALID).countryCode("212").country(MOROCCO).build();
        var phone02 = PhoneNumber.builder().number("6007989253").status(INVALID).countryCode("212").country(MOROCCO).build();
        var phone03 = PhoneNumber.builder().number("847651504").status(VALID).countryCode("258").country(MOZAMBIQUE).build();
        var phone04 = PhoneNumber.builder().number("846565883").status(INVALID).countryCode("258").country(MOZAMBIQUE).build();
        var phone05 = PhoneNumber.builder().number("7503O6263").status(VALID).countryCode("256").country(UGANDA).build();
        var phone06 = PhoneNumber.builder().number("704244430").status(INVALID).countryCode("256").country(UGANDA).build();
        var phone07 = PhoneNumber.builder().number("914701723").status(VALID).countryCode("251").country(ETHIOPIA).build();
        var phone08 = PhoneNumber.builder().number("911203317").status(INVALID).countryCode("251").country(ETHIOPIA).build();
        var phone09 = PhoneNumber.builder().number("6780009592").status(VALID).countryCode("237").country(CAMEROON).build();
        var phone10 = PhoneNumber.builder().number("6622284920").status(INVALID).countryCode("237").country(CAMEROON).build();

        var phones = of(phone01, phone02, phone03, phone04, phone05, phone06, phone07, phone08, phone09, phone10).toList();
        var returned = CustomPage.builder().page(0).size(10).content(phones).build();

        when(customerService.getAllPhoneNumbers(null, null, Pageable.ofSize(10))).thenReturn(returned);

        String numbersAsString = get(uri + "/api/v1/customers/phones?size=10&page=0").then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("content.size()", is(10))
                .extract()
                .body()
                .jsonPath()
                .getList("content", PhoneNumber.class)
                .stream()
                .map(PhoneNumber::getNumber)
                .collect(Collectors.joining(","));

        assertEquals("6546545369,6007989253,847651504,846565883,7503O6263,704244430,914701723,911203317,6780009592,6622284920", numbersAsString);
    }

    @Test
    void should_return_all_filtered_phone_numbers_by_a_valid_country() {
        var phone01 = PhoneNumber.builder().number("6546545369").status(VALID).countryCode("212").country(MOROCCO).build();
        var phone02 = PhoneNumber.builder().number("6007989253").status(INVALID).countryCode("212").country(MOROCCO).build();

        var phones = of(phone01, phone02).toList();
        var returned = CustomPage.builder().page(0).size(10).content(phones).build();

        when(customerService.getAllPhoneNumbers(MOROCCO, null, Pageable.ofSize(10))).thenReturn(returned);

        String countryAsString = get(uri + "/api/v1/customers/phones?country=MOROCCO&page=0&size=10").then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("content.size()", is(2))
                .extract()
                .body()
                .jsonPath()
                .getList("content", PhoneNumber.class)
                .stream()
                .map(i -> i.getCountry().name())
                .collect(Collectors.joining(","));

        assertEquals("MOROCCO,MOROCCO", countryAsString);
    }

    @Test
    void should_return_all_filtered_phone_numbers_by_a_valid_status() {
        var phone01 = PhoneNumber.builder().number("6546545369").status(VALID).countryCode("212").country(MOROCCO).build();
        var phone03 = PhoneNumber.builder().number("847651504").status(VALID).countryCode("258").country(MOZAMBIQUE).build();

        var phones = of(phone01, phone03).toList();
        var returned = CustomPage.builder().page(0).size(10).content(phones).build();

        when(customerService.getAllPhoneNumbers(null, VALID, Pageable.ofSize(10))).thenReturn(returned);

        String statusAsString = get(uri + "/api/v1/customers/phones?status=VALID&page=0&size=10").then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("content.size()", is(2))
                .extract()
                .body()
                .jsonPath()
                .getList("content", PhoneNumber.class)
                .stream()
                .map(i -> i.getStatus().name())
                .collect(Collectors.joining(","));

        assertEquals("VALID,VALID", statusAsString);
    }
}
