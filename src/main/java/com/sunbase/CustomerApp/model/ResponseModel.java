package com.sunbase.CustomerApp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sunbase.CustomerApp.util.Constants;

/**
 * ResponseModel class representing the structure of API responses.
 */
@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel {
    private String statusCode = Constants.failureCode;
    private String status = Constants.failureStatus;
    private String message;
    private Data data;
}
