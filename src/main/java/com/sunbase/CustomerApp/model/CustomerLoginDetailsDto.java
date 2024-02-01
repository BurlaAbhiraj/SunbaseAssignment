package com.sunbase.CustomerApp.model;

import lombok.Data;
/**
 * Data Transfer Object (DTO) representing the login details for customer authentication.
 */
@Data
public class CustomerLoginDetailsDto {
    private String login_id;  // The login ID associated with the customer.
    private String password;  // The password for customer authentication.
}
