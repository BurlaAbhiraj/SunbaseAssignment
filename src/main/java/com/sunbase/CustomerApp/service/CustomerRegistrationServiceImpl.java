package com.sunbase.CustomerApp.service;


import com.sunbase.CustomerApp.util.CommonValidater;
import com.sunbase.CustomerApp.util.Constants;
import com.sunbase.CustomerApp.model.*;
import com.sunbase.CustomerApp.model.CustomerRegistration;
import com.sunbase.CustomerApp.repositories.CustomerRegistrationRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Implementation of the {@link CustomerRegistrationService} interface.
 */
@Service
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService {
    @Autowired
    private CustomerRegistrationRepo customerRegistrationRepo;

    /**
     * Register a new customer.
     *
     * @param customerRegistrationDetailsDto The details of the customer to be registered.
     * @return A ResponseModel indicating the success or failure of the registration.
     */
    @Override
    public ResponseModel registerCustomer(CustomerRegistrationDetailsDto customerRegistrationDetailsDto) {
        ResponseModel responseModel = new ResponseModel();
        CustomerRegistration customerRegistration = new CustomerRegistration();
        CustomerValidationResponse customerValidationResponse = CommonValidater.validateCustomerRegistration(customerRegistrationDetailsDto);
        if (customerValidationResponse.getStatus().equalsIgnoreCase("false")) {
            responseModel.setMessage(customerValidationResponse.getMessage());
            return responseModel;
        }
        // Generate a random long value
        long randomId = System.currentTimeMillis();


        BeanUtils.copyProperties(customerRegistrationDetailsDto, customerRegistration);
        customerRegistration.setUuid(randomId + "");
        CustomerRegistration returnedCustomerRegistration = customerRegistrationRepo.save(customerRegistration);
        if (!(returnedCustomerRegistration == null)) {
            responseModel.setStatusCode(Constants.successCode);
            responseModel.setStatus(Constants.successStatus);
            responseModel.setMessage("Customer Created Successfully");
            Data data = new Data();
            CustomerRegistrationDetailsDto registeredCustomer = new CustomerRegistrationDetailsDto();
            BeanUtils.copyProperties(returnedCustomerRegistration, registeredCustomer);
            data.setCustomerRegistrationDetailsDto(registeredCustomer);
            responseModel.setData(data);
        } else {
            responseModel.setMessage("Customer Creation Failed");
        }
        return responseModel;
    }

    /**
     * Delete a customer by their ID.
     *
     * @param id The ID of the customer to be deleted.
     * @return A ResponseModel indicating the success or failure of the deletion.
     */
    @Override
    @Transactional
    public ResponseModel deleteCustomer(String id) {
        ResponseModel responseModel = new ResponseModel();
        List<CustomerDataDto> returnCustList = new ArrayList<>();
        Data data = new Data();
        Long customerId = Long.valueOf(id);
        customerRegistrationRepo.deleteById(customerId);
        List<CustomerRegistration> list = customerRegistrationRepo.findAll();
        for (CustomerRegistration customer : list) {
            CustomerDataDto customerDataDto = new CustomerDataDto();
            BeanUtils.copyProperties(customer, customerDataDto);
            returnCustList.add(customerDataDto);
        }
        data.setCustomerDetailsList(returnCustList);
        responseModel.setData(data);
        responseModel.setStatusCode(Constants.successCode);
        responseModel.setStatus(Constants.successStatus);
        responseModel.setMessage("Customer deleted successfully!");
        return responseModel;
    }

    /**
     * Synchronize customer data from a remote API and save it in the local database.
     *
     * @param token The access token required for synchronization.
     * @return A ResponseModel indicating the success or failure of the synchronization.
     */
    @Override
    public ResponseModel syncCustomer(String token) {
        ResponseModel responseModel = new ResponseModel();
        Data data = new Data();
        List<CustomerDataDto> returnCustList = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);
        System.out.println("token : " + token);
        String url = "https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<List<CustomerDataDto>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                    requestEntity, new ParameterizedTypeReference<List<CustomerDataDto>>() {
                    });

            System.out.println("responseEntity : " + responseEntity);
            List<CustomerDataDto> customerDataList = responseEntity.getBody();
            System.out.println(customerDataList);

            // save the customers received from remote api
            for (CustomerDataDto customer : customerDataList) {

                // existence of fetched customer is being checked on behalf of their uuid only
                // it can be done on other field or multiple fields
                Optional<CustomerRegistration> existingCustomerRegistration = customerRegistrationRepo.findByUuid(customer.getUuid());
                if (existingCustomerRegistration.isPresent()) {
                    continue;
                }
                CustomerRegistration customerRegistration = new CustomerRegistration();
                BeanUtils.copyProperties(customer, customerRegistration);
                customerRegistrationRepo.save(customerRegistration);
            }
            responseModel.setMessage("Syncing completed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            responseModel.setMessage("Exception from remote api");
        }
        // get all customer from database to return
        returnCustList.clear();  // clear the previous list to use again
        List<CustomerRegistration> list = customerRegistrationRepo.findAll();
        System.out.println(list);
        for (CustomerRegistration customer : list) {
            CustomerDataDto customerDataDto = new CustomerDataDto();
            BeanUtils.copyProperties(customer, customerDataDto);
            returnCustList.add(customerDataDto);
        }
        data.setCustomerDetailsList(returnCustList);
        responseModel.setData(data);
        return responseModel;
    }
    /**
     * Update an existing customer's information.
     *
     * @param customerDataDto The updated details of the customer.
     * @return A ResponseModel indicating the success or failure of the update.
     */
    @Override
    public ResponseModel updateCustomer(CustomerDataDto customerDataDto) {
        ResponseModel responseModel = new ResponseModel();
        Optional<CustomerRegistration> existingCustomerRegistration = customerRegistrationRepo.findById(customerDataDto.getId());
        if (existingCustomerRegistration.isPresent()) {
            CustomerRegistration existingEntity = existingCustomerRegistration.get();
            BeanUtils.copyProperties(customerDataDto, existingEntity);
            customerRegistrationRepo.updateCustomerRegistration(existingEntity);
            responseModel.setStatus(Constants.successStatus);
            responseModel.setStatusCode(Constants.successCode);
            responseModel.setMessage("Customer Updated Successfully!");
        } else {
            responseModel.setMessage("No such customer!");
        }
        return responseModel;
    }

}
