package com.sunbase.CustomerApp.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
/**
 * Data class representing various customer-related data.
 */
@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {
    private CustomerRegistrationDetailsDto customerRegistrationDetailsDto;
    private CustomerSuccessLoginDetailsDto customerSuccessLoginDetailsDto;
    private List<CustomerDataDto> customerDetailsList;
    private CustomerDataDto customerDataDto;
}
