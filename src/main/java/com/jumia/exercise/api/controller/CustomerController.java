package com.jumia.exercise.api.controller;

import com.jumia.exercise.model.Country;
import com.jumia.exercise.model.PhoneState;
import com.jumia.exercise.service.CustomerService;
import com.jumia.exercise.vo.CustomPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/customers")
public class CustomerController {

    private CustomerService service;

    @GetMapping(value = "phones")
    public CustomPage getAllPhoneNumbers(Country country, PhoneState state, Pageable pageable) {
        log.info("RETURNING ALL PHONE NUMBERS");
        var allPhoneNumbers = service.getAllPhoneNumbers(country, state, pageable);
        log.debug("RETURNING {} PHONE(S) NUMBER(S)", allPhoneNumbers.getSize());
        return allPhoneNumbers;
    }
}
