package service;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CustomerRepository;

import java.util.Optional;

/**
 * Service class for <code>Customer<code/>.
 */
@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void createCustomer(Customer customer) {
        this.customerRepository.save(customer);
    }
    public Optional<Customer> findCustomerById(Long id) {
        return this.customerRepository.findById(id);
    }

}
