package com.sunbase.CustomerApp.model;

import lombok.Data;
/**
 * Data Transfer Object (DTO) representing customer data.
 * It is used for transferring customer-related information between layers of the application.
 */
@Data
public class CustomerDataDto {
    /**
     * Unique identifier for the customer.
     */
    private Long id;

    /**
     * UUID (Universally Unique Identifier) for the customer.
     */
    private String uuid;

    /**
     * First name of the customer.
     */
    private String first_name;

    /**
     * Last name of the customer.
     */
    private String last_name;

    /**
     * Street information of the customer's address.
     */
    private String street;

    /**
     * Detailed address information of the customer.
     */
    private String address;

    /**
     * City where the customer resides.
     */
    private String city;

    /**
     * State or region where the customer resides.
     */
    private String state;

    /**
     * Email address of the customer.
     */
    private String email;

    /**
     * Phone number of the customer.
     */
    private String phone;
}
