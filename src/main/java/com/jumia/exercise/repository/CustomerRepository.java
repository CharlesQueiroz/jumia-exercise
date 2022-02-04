package com.jumia.exercise.repository;

import com.jumia.exercise.model.Customer;
import com.jumia.exercise.vo.PhoneNumber;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

    @Query("SELECT c.phone FROM Customer c")
    Stream<PhoneNumber> findAllPhoneNumbers();
}
