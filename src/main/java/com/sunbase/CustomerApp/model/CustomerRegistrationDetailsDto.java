package com.sunbase.CustomerApp.model;

import lombok.Data;
/**
 * DTO (Data Transfer Object) class representing customer registration details received from the client.
 */
@Data
public class CustomerRegistrationDetailsDto {
    private String first_name;  // First name of the customer.

    private String last_name;  // Last name of the customer.

    private String street;  // Street information for the customer address.

    private String address;  // Additional address information for the customer.

    private String city;  // City of residence for the customer.

    private String state;  // State or region of residence for the customer.

    private String email;  // Email address of the customer.

    private String phone;  // Phone number of the customer.


}
