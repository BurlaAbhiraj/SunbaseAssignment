package com.sunbase.CustomerApp.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity class representing customer registration details stored in the database.
 */
@Data
@Entity
@Table(name = "sunbase_user_details")

public class CustomerRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Unique identifier for the customer registration.

    private String uuid;  // Unique identifier for the customer.

    private String first_name;  // First name of the customer.

    private String last_name;  // Last name of the customer.

    private String street;  // Street information for the customer address.

    private String address;  // Additional address information for the customer.

    private String city;  // City of residence for the customer.

    private String state;  // State or region of residence for the customer.

    private String email;  // Email address of the customer.

    private String phone;  // Phone number of the customer.

}
