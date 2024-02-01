package com.sunbase.CustomerApp.service;


import com.sunbase.CustomerApp.model.ResponseModel;

public interface CustomerService {
    public ResponseModel getAllCustomer();

    public ResponseModel getCustomerById(String id);
}
