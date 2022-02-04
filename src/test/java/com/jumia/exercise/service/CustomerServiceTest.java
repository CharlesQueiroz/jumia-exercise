package com.jumia.exercise.service;

import com.jumia.exercise.repository.CustomerRepository;
import com.jumia.exercise.vo.CustomPage;
import com.jumia.exercise.vo.PhoneNumber;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static com.jumia.exercise.model.Country.*;
import static com.jumia.exercise.model.PhoneState.INVALID;
import static com.jumia.exercise.model.PhoneState.VALID;
import static java.util.Arrays.asList;
import static java.util.stream.Stream.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Pageable.ofSize;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    void should_return_all_phone_numbers() {
        var phone01 = PhoneNumber.builder().number("6546545369").state(VALID).countryCode("212").country(MOROCCO).build();
        var phone02 = PhoneNumber.builder().number("6007989253").state(INVALID).countryCode("212").country(MOROCCO).build();
        var phone03 = PhoneNumber.builder().number("847651504").state(VALID).countryCode("258").country(MOZAMBIQUE).build();
        var phone04 = PhoneNumber.builder().number("846565883").state(INVALID).countryCode("258").country(MOZAMBIQUE).build();
        var phone05 = PhoneNumber.builder().number("7503O6263").state(VALID).countryCode("256").country(UGANDA).build();
        var phone06 = PhoneNumber.builder().number("704244430").state(INVALID).countryCode("256").country(UGANDA).build();
        var phone07 = PhoneNumber.builder().number("914701723").state(VALID).countryCode("251").country(ETHIOPIA).build();
        var phone08 = PhoneNumber.builder().number("911203317").state(INVALID).countryCode("251").country(ETHIOPIA).build();
        var phone09 = PhoneNumber.builder().number("6780009592").state(VALID).countryCode("237").country(CAMEROON).build();
        var phone10 = PhoneNumber.builder().number("6622284920").state(INVALID).countryCode("237").country(CAMEROON).build();

        var phones = of(phone01, phone02, phone03, phone04, phone05, phone06, phone07, phone08, phone09, phone10);
        var expected = asList(phone01, phone02, phone03, phone04, phone05, phone06, phone07, phone08, phone09, phone10);

        when(customerRepository.findAllPhoneNumbers()).thenReturn(phones);

        var customPage = CustomPage.builder().page(0).size(10).content(expected).build();
        var returned = customerService.getAllPhoneNumbers(null, null, ofSize(10));

        assertEquals(customPage.getPage(), returned.getPage());
        assertEquals(customPage.getSize(), returned.getSize());
        assertEquals(customPage.getContent().size(), returned.getContent().size());
    }

    @Test
    void should_return_all_filtered_phone_numbers_by_a_valid_country() {
        var phone01 = PhoneNumber.builder().number("6546545369").state(VALID).countryCode("212").country(MOROCCO).build();
        var phone02 = PhoneNumber.builder().number("6007989253").state(INVALID).countryCode("212").country(MOROCCO).build();
        var phone03 = PhoneNumber.builder().number("847651504").state(VALID).countryCode("258").country(MOZAMBIQUE).build();

        var phones = of(phone01, phone02, phone03);
        var expected = asList(phone01, phone02);

        when(customerRepository.findAllPhoneNumbers()).thenReturn(phones);

        var customPage = CustomPage.builder().page(0).size(2).content(expected).build();
        var returned = customerService.getAllPhoneNumbers(MOROCCO, null, ofSize(2));

        assertEquals(customPage.getPage(), returned.getPage());
        assertEquals(customPage.getSize(), returned.getSize());
        var content = returned.getContent();
        content.forEach(p -> assertSame(MOROCCO, p.getCountry()));
        assertEquals(customPage.getContent().size(), content.size());
    }

    @Test
    void should_return_all_filtered_phone_numbers_by_a_invalid_country() {
        var phone01 = PhoneNumber.builder().number("6546545369").state(VALID).countryCode("212").country(MOROCCO).build();
        var phone02 = PhoneNumber.builder().number("847651504").state(VALID).countryCode("258").country(MOZAMBIQUE).build();

        var phones = of(phone01, phone02);
        var expected = new ArrayList<PhoneNumber>();

        when(customerRepository.findAllPhoneNumbers()).thenReturn(phones);

        var customPage = CustomPage.builder().page(0).size(0).content(expected).build();
        var returned = customerService.getAllPhoneNumbers(NONE, null, ofSize(10));

        assertEquals(customPage.getPage(), returned.getPage());
        assertEquals(customPage.getSize(), returned.getSize());
        assertEquals(customPage.getContent().size(), returned.getContent().size());
    }

    @Test
    void should_return_all_filtered_phone_numbers_by_a_valid_state() {
        var phone01 = PhoneNumber.builder().number("6546545369").state(VALID).countryCode("212").country(MOROCCO).build();
        var phone02 = PhoneNumber.builder().number("6007989253").state(INVALID).countryCode("212").country(MOROCCO).build();
        var phone03 = PhoneNumber.builder().number("847651504").state(VALID).countryCode("258").country(MOZAMBIQUE).build();

        var phones = of(phone01, phone02, phone03);
        var expected = asList(phone01, phone03);

        when(customerRepository.findAllPhoneNumbers()).thenReturn(phones);

        var customPage = CustomPage.builder().page(0).size(2).content(expected).build();
        var returned = customerService.getAllPhoneNumbers(null, VALID, ofSize(10));

        assertEquals(customPage.getPage(), returned.getPage());
        assertEquals(customPage.getSize(), returned.getSize());

        var content = returned.getContent();
        content.forEach(p -> assertSame(VALID, p.getState()));

        assertEquals(customPage.getContent().size(), returned.getContent().size());
    }

    @Test
    void should_return_all_filtered_phone_numbers_by_a_invalid_state() {
        var phone01 = PhoneNumber.builder().number("6546545369").state(VALID).countryCode("212").country(MOROCCO).build();
        var phone02 = PhoneNumber.builder().number("6007989253").state(INVALID).countryCode("212").country(MOROCCO).build();
        var phone03 = PhoneNumber.builder().number("847651504").state(VALID).countryCode("258").country(MOZAMBIQUE).build();

        var phones = of(phone01, phone02, phone03);
        var expected = List.of(phone02);

        when(customerRepository.findAllPhoneNumbers()).thenReturn(phones);

        var customPage = CustomPage.builder().page(0).size(1).content(expected).build();
        var returned = customerService.getAllPhoneNumbers(null, INVALID, ofSize(10));

        assertEquals(customPage.getPage(), returned.getPage());
        assertEquals(customPage.getSize(), returned.getSize());

        var content = returned.getContent();
        content.forEach(p -> assertSame(INVALID, p.getState()));

        assertEquals(customPage.getContent().size(), content.size());
    }

    @Test
    void should_return_all_filtered_phone_numbers_by_a_valid_country_and_state() {
        var phone01 = PhoneNumber.builder().number("6546545369").state(VALID).countryCode("212").country(MOROCCO).build();
        var phone02 = PhoneNumber.builder().number("6007989253").state(INVALID).countryCode("212").country(MOROCCO).build();
        var phone03 = PhoneNumber.builder().number("847651504").state(VALID).countryCode("258").country(MOZAMBIQUE).build();
        var phone04 = PhoneNumber.builder().number("846565883").state(INVALID).countryCode("258").country(MOZAMBIQUE).build();

        var phones = of(phone01, phone02, phone03, phone04);
        var expected = List.of(phone03);

        when(customerRepository.findAllPhoneNumbers()).thenReturn(phones);

        var customPage = CustomPage.builder().page(0).size(1).content(expected).build();
        var returned = customerService.getAllPhoneNumbers(MOZAMBIQUE, VALID, ofSize(10));

        assertEquals(customPage.getPage(), returned.getPage());
        assertEquals(customPage.getSize(), returned.getSize());

        var content = returned.getContent();

        content.forEach(p -> {
            assertSame(MOZAMBIQUE, p.getCountry());
            assertSame(VALID, p.getState());
        });

        assertEquals(customPage.getContent().size(), content.size());
    }

    @Test
    void should_return_all_filtered_phone_numbers_by_a_valid_first_page() {
        var phone01 = PhoneNumber.builder().number("6546545369").state(VALID).countryCode("212").country(MOROCCO).build();
        var phone02 = PhoneNumber.builder().number("6007989253").state(INVALID).countryCode("212").country(MOROCCO).build();
        var phone03 = PhoneNumber.builder().number("847651504").state(VALID).countryCode("258").country(MOZAMBIQUE).build();
        var phone04 = PhoneNumber.builder().number("846565883").state(INVALID).countryCode("258").country(MOZAMBIQUE).build();

        var phones = of(phone01, phone02, phone03, phone04);
        var expected = List.of(phone01, phone02);

        when(customerRepository.findAllPhoneNumbers()).thenReturn(phones);

        var customPage = CustomPage.builder().page(0).size(2).content(expected).build();
        var returned = customerService.getAllPhoneNumbers(null, null, ofSize(2));

        assertEquals(customPage.getPage(), returned.getPage());
        assertEquals(customPage.getSize(), returned.getSize());
        var content = returned.getContent().stream().toList();

        assertEquals(content.get(0).getNumber(), phone01.getNumber());
        assertEquals(content.get(1).getNumber(), phone02.getNumber());
        assertEquals(customPage.getContent().size(), returned.getContent().size());
    }

    @Test
    void should_return_all_filtered_phone_numbers_by_a_valid_second_page() {
        var phone01 = PhoneNumber.builder().number("6546545369").state(VALID).countryCode("212").country(MOROCCO).build();
        var phone02 = PhoneNumber.builder().number("6007989253").state(INVALID).countryCode("212").country(MOROCCO).build();
        var phone03 = PhoneNumber.builder().number("847651504").state(VALID).countryCode("258").country(MOZAMBIQUE).build();
        var phone04 = PhoneNumber.builder().number("846565883").state(INVALID).countryCode("258").country(MOZAMBIQUE).build();

        var phones = of(phone01, phone02, phone03, phone04);
        var expected = List.of(phone03, phone04);

        when(customerRepository.findAllPhoneNumbers()).thenReturn(phones);

        var customPage = CustomPage.builder().page(1).size(2).content(expected).build();
        var returned = customerService.getAllPhoneNumbers(null, null, PageRequest.of(1, 2));

        assertEquals(customPage.getPage(), returned.getPage());
        assertEquals(customPage.getSize(), returned.getSize());
        var content = returned.getContent().stream().toList();

        assertEquals(content.get(0).getNumber(), phone03.getNumber());
        assertEquals(content.get(1).getNumber(), phone04.getNumber());
        assertEquals(customPage.getContent().size(), returned.getContent().size());
    }
}
