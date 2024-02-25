package com.securityjwt.demojwt.repository;

import com.securityjwt.demojwt.model.account.Account;
import com.securityjwt.demojwt.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByAccountId(Long accountId);
}
