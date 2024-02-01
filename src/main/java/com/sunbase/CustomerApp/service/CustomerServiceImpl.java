package com.sunbase.CustomerApp.service;


import com.sunbase.CustomerApp.util.Constants;
import com.sunbase.CustomerApp.model.CustomerDataDto;
import com.sunbase.CustomerApp.model.Data;
import com.sunbase.CustomerApp.model.ResponseModel;
import com.sunbase.CustomerApp.model.CustomerRegistration;
import com.sunbase.CustomerApp.repositories.CustomerRegistrationRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * Implementation of the {@link CustomerService} interface.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRegistrationRepo customerRegistrationRepo;
    /**
     * Retrieves a list of all customers.
     *
     * @return ResponseModel containing the list of customers.
     */
    @Override
    public ResponseModel getAllCustomer() {
        ResponseModel responseModel = new ResponseModel();
        List<CustomerRegistration> customerRegistration = customerRegistrationRepo.findAll();
        List<CustomerDataDto> customerList = new ArrayList<>();

        for (CustomerRegistration obj : customerRegistration) {
            CustomerDataDto customer = new CustomerDataDto();
            BeanUtils.copyProperties(obj, customer);
            customerList.add(customer);
        }
        Data data = new Data();
        responseModel.setStatus(Constants.successStatus);
        responseModel.setStatusCode(Constants.successCode);
        data.setCustomerDetailsList(customerList);
        responseModel.setData(data);
        return responseModel;
    }
    /**
     * Retrieves a customer by their ID.
     *
     * @param id The ID of the customer to retrieve.
     * @return ResponseModel containing the customer data.
     * @throws RuntimeException if the customer is not found.
     */
    @Override
    public ResponseModel getCustomerById(String id) {
        Long customerId = Long.valueOf(id);
        ResponseModel responseModel = new ResponseModel();
        CustomerRegistration customerRegistration = customerRegistrationRepo.findById(customerId).orElseThrow(() -> new RuntimeException("Customer with ID " + customerId + " not found"));

        CustomerDataDto customer = new CustomerDataDto();
        BeanUtils.copyProperties(customerRegistration, customer);

        Data data = new Data();
        data.setCustomerDataDto(customer);
        responseModel.setStatus(Constants.successStatus);
        responseModel.setStatusCode(Constants.successCode);
        responseModel.setData(data);
        return responseModel;
    }

}
