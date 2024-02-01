package com.sunbase.CustomerApp.service;


import com.sunbase.CustomerApp.model.CustomerDataDto;
import com.sunbase.CustomerApp.model.CustomerRegistrationDetailsDto;
import com.sunbase.CustomerApp.model.ResponseModel;
/**
 * Service interface for customer registration operations.
 */
public interface CustomerRegistrationService {
    /**
     * Register a new customer.
     *
     * @param customerRegistrationDetailsDto The details of the customer to be registered.
     * @return A ResponseModel indicating the success or failure of the registration.
     */
    public ResponseModel registerCustomer(CustomerRegistrationDetailsDto customerRegistrationDetailsDto);
    /**
     * Update an existing customer's information.
     *
     * @param customerDataDto The updated details of the customer.
     * @return A ResponseModel indicating the success or failure of the update.
     */

    public ResponseModel updateCustomer(CustomerDataDto customerDataDto);
    /**
     * Delete a customer by their ID.
     *
     * @param id The ID of the customer to be deleted.
     * @return A ResponseModel indicating the success or failure of the deletion.
     */
    public ResponseModel deleteCustomer(String id);
    /**
     * Synchronize customer data from a remote API and save it in the local database.
     *
     * @param token The access token required for synchronization.
     * @return A ResponseModel indicating the success or failure of the synchronization.
     */
    ResponseModel syncCustomer(String token);
}
