package com.sunbase.CustomerApp.model;


import com.sunbase.CustomerApp.util.Constants;
import lombok.Data;
/**
 * DTO (Data Transfer Object) class representing the response of customer validation.
 */
@Data
public class CustomerValidationResponse {
    private String status = "false";
    private String message = Constants.TECHNICAL_ERROR;
}
