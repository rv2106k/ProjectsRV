package com.project.carrental.service;

import com.project.carrental.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> listAll();
    Customer findById(Long id);
    Customer save(Customer customer);
    Customer update(Long id, Customer customer);
    void delete(Long id);
}

