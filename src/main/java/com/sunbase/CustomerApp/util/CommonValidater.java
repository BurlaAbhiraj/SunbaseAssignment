package com.sunbase.CustomerApp.util;


import com.sunbase.CustomerApp.model.CustomerRegistrationDetailsDto;
import com.sunbase.CustomerApp.model.CustomerValidationResponse;
/**
 * Utility class for validating customer registration details.
 * It provides a static method to validate the required fields in a CustomerRegistrationDetailsDto.
 */
public class CommonValidater {
    /**
     * Validates the customer registration details and returns a validation response.
     *
     * @param customerRegistrationDetailsDto The details of the customer registration to be validated.
     * @return A CustomerValidationResponse indicating the validation status and message.
     */
    public static CustomerValidationResponse validateCustomerRegistration(CustomerRegistrationDetailsDto customerRegistrationDetailsDto) {
        CustomerValidationResponse customerValidationResponse = new CustomerValidationResponse();
        // Check if email is empty or null
        if (customerRegistrationDetailsDto.getEmail().isEmpty() || customerRegistrationDetailsDto.getEmail().equals("") || customerRegistrationDetailsDto.getEmail() == null) {
            customerValidationResponse.setStatus("false");
            customerValidationResponse.setMessage("Mobile Number can't be empty!");
            return customerValidationResponse;
        }
        // Check if first name is empty or null
        if (customerRegistrationDetailsDto.getFirst_name().isEmpty() || customerRegistrationDetailsDto.getFirst_name().equals("") || customerRegistrationDetailsDto.getFirst_name() == null) {
            customerValidationResponse.setStatus("false");
            customerValidationResponse.setMessage("First Name can't be empty!");
        }
        // If both email and first name are valid, set status to true
        customerValidationResponse.setStatus("true");
        return customerValidationResponse;
    }
}
