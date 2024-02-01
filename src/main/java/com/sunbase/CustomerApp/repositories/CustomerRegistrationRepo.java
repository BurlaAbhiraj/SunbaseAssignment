package com.sunbase.CustomerApp.repositories;


import com.sunbase.CustomerApp.model.CustomerRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Repository interface for CustomerRegistration entity.
 */
@Repository
public interface CustomerRegistrationRepo extends JpaRepository<CustomerRegistration, Long> {
    /**
     * Find a customer by its ID.
     *
     * @param id The ID of the customer.
     * @return An Optional containing the customer if found.
     */
    Optional<CustomerRegistration> findById(Long id);
    /**
     * Find a customer by its UUID.
     *
     * @param id The UUID of the customer.
     * @return An Optional containing the customer if found.
     */
    Optional<CustomerRegistration> findByUuid(String id);
    /**
     * Default method to update a customer registration.
     *
     * @param customerRegistration The customer registration to be updated.
     * @return The updated customer registration.
     */
    default CustomerRegistration updateCustomerRegistration(CustomerRegistration customerRegistration) {
        return save(customerRegistration);
    }
}
