package com.sunbase.CustomerApp.service;


import com.sunbase.CustomerApp.model.CustomerLoginDetailsDto;
/**
 * Service interface for customer login operations.
 */
public interface CustomerLoginService {
    /**
     * Make an API call for customer login.
     *
     * @param customerLoginDetailsDto The login details of the customer.
     * @return The result of the API call, typically an access token.
     */
    String apiCall(CustomerLoginDetailsDto customerLoginDetailsDto);
}
