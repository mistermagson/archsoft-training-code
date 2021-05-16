package com.archsoft.service;

import com.archsoft.exception.RecordNotFoundException;
import com.archsoft.model.customer.Customer;
import com.archsoft.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(Customer customer) {
        return customerRepository.insert(customer);
    }

    public Customer find(String id) throws RecordNotFoundException {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Customer " + id + " not found"));
    }

    public Customer findByEmail(String email) throws RecordNotFoundException {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new RecordNotFoundException("Customer " + email + " not found"));
    }

    public Customer update(Customer customer) throws RecordNotFoundException {
        Customer customerCurrent = customerRepository.findById(customer.getId())
                .orElseThrow(() -> new RecordNotFoundException("Customer " + customer.getId() + " not found"));

        merge(customerCurrent, customer);

        return customerRepository.save(customerCurrent);
    }

    private void merge(Customer customerCurrent, Customer customer) {
        Optional.ofNullable(customer.getName()).ifPresent(s -> customerCurrent.setName(s));
        Optional.ofNullable(customer.getEmail()).ifPresent(s -> customerCurrent.setEmail(s));
        Optional.ofNullable(customer.getAddresses()).ifPresent(addresses -> customerCurrent.setAddresses(addresses));
    }

    public List<Customer> list() {
        return customerRepository.findAll();
    }

    public Customer delete(String id) throws RecordNotFoundException {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
        customerRepository.deleteById(id);

        return customer;
    }

    public boolean validate(String customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        return customerOptional.isPresent() && customerOptional.get().isEnabled();
    }
}
