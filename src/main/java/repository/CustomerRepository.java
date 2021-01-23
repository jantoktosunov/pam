package repository;

import model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class for <code>Customer</code>.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
