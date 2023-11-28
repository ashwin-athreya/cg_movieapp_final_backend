package com.cg.mts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.mts.entity.Customer;
import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public Customer addCustomer(Customer customer) throws CustomerNotFoundException {
	    if (customer != null) {
	        if (customerRepository.existsById(customer.getCustomerId())) {
	            throw new CustomerNotFoundException("Customer " + customer.getCustomerId() + " is already Exists");
	        } else if (customer.getMobileNumber() != null && customerRepository.existsByMobileNumber(customer.getMobileNumber())) {
	            throw new CustomerNotFoundException(
	                    "Customer with number " + customer.getMobileNumber() + " is already Exists");
	        } else if (customer.getEmail() != null && customerRepository.existsByEmail(customer.getEmail())) {
	            throw new CustomerNotFoundException(
	                    "Customer with email " + customer.getEmail() + " is already Exists");
	        } else {
	            // Save the customer to the repository
	            return customerRepository.save(customer);
	        }
	    }
	    return null; // You might want to handle this case based on your application's requirements
	}



	@Override
	public List<Customer> viewCustomerList() {
		return customerRepository.findAll();
	}

	@Override
	public Customer removeCustomer(int customerid) {
		Customer c = customerRepository.getOne(customerid);
		customerRepository.delete(c);
		return c;
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
		
		System.out.println(customer.getCustomerId());
		customerRepository.saveAndFlush(customer);
		return customerRepository.getOne(customer.getCustomerId());
	}

	@Override
	public Customer viewCustomer(int customerid) {
		return customerRepository.findById(customerid).get();
	}

	@Override
	public boolean existsById(Integer userId) {
		return customerRepository.existsById(userId);
	}

	@Override
	public boolean existsByMobileNumber(String mobileNo) {

		return customerRepository.existsByMobileNumber(mobileNo);
	}

	@Override
	public boolean existsByEmail(String email) {

		return customerRepository.existsByEmail(email);
	}

}