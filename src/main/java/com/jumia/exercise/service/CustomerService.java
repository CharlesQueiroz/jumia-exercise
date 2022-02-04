package com.jumia.exercise.service;

import com.jumia.exercise.model.Country;
import com.jumia.exercise.model.PhoneStatus;
import com.jumia.exercise.repository.CustomerRepository;
import com.jumia.exercise.vo.CustomPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;

    @Transactional
    public CustomPage getAllPhoneNumbers(Country country, PhoneStatus status, Pageable paging) {
        log.info("GETTING ALL PHONE NUMBERS FOR COUNTRY: {}, STATE: {}", country, status);

        var allPhoneNumbers = customerRepository.findAllPhoneNumbers()
                .filter(p -> status == null || p.getStatus() == status)
                .filter(p -> country == null || p.getCountry() == country)
                .skip((long) paging.getPageNumber() * paging.getPageSize())
                .limit(paging.getPageSize())
                .toList();

        var customPage = CustomPage.builder()
                .content(allPhoneNumbers)
                .size(allPhoneNumbers.size())
                .page(paging.getPageNumber())
                .build();

        log.debug("RETURNING PAGE: {}", customPage);

        return customPage;
    }
}
