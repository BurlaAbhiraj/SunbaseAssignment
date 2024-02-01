package com.sunbase.CustomerApp.controller;

import com.sunbase.CustomerApp.model.CustomerDataDto;
import com.sunbase.CustomerApp.model.CustomerLoginDetailsDto;
import com.sunbase.CustomerApp.model.ResponseModel;
import com.sunbase.CustomerApp.security.JwtUtils;
import com.sunbase.CustomerApp.service.CustomerRegistrationService;
import com.sunbase.CustomerApp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
/**
 * Controller class for managing customer-related operations.
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private CustomerService findcustomerService;
    @Autowired
    private CustomerRegistrationService customerRegistrationService;
    /**
     * Endpoint to delete a customer.
     *
     * @param id    The ID of the customer to be deleted.
     * @param token The authentication token.
     * @return ModelAndView containing the updated dashboard view.
     */

    @GetMapping("/delete-customer/{id}/{token}")
    public ModelAndView deleteCustomer(@PathVariable String id, @PathVariable String token){
        System.out.println(token);
        ModelAndView modelAndView = new ModelAndView();
        ResponseModel responseModel = customerRegistrationService.deleteCustomer(id);
        modelAndView.addObject("message",responseModel.getMessage());
        modelAndView.addObject("token",token);
        modelAndView.addObject("customerList",responseModel.getData().getCustomerDetailsList());
        modelAndView.setViewName("Dashboard");
        return modelAndView;
    }
    /**
     * Endpoint to sync customer data.
     *
     * @param token                   The authentication token.
     * @param customerLoginDetailsDto DTO containing customer login details.
     * @return ModelAndView containing the updated dashboard view.
     */

    @PostMapping("/sync-customer/{token}")
    public ModelAndView syncCustomer(@PathVariable String token, @ModelAttribute CustomerLoginDetailsDto customerLoginDetailsDto){
        ModelAndView modelAndView = new ModelAndView();
        ResponseModel responseModel = customerRegistrationService.syncCustomer(token);
        modelAndView.addObject("token",token);
        modelAndView.addObject("message",responseModel.getMessage());
        modelAndView.addObject("customerList",responseModel.getData().getCustomerDetailsList());
        modelAndView.setViewName("Dashboard");
        return modelAndView;
    }
    /**
     * Endpoint to update customer data.
     *
     * @param customerDataDto DTO containing customer data to be updated.
     * @return ResponseEntity containing the response model and HTTP status.
     */
    @PutMapping("/update-customer")
    public ResponseEntity<ResponseModel> updateCustomer(@RequestBody CustomerDataDto customerDataDto){
        ResponseModel responseModel = new ResponseModel();
        responseModel = customerRegistrationService.updateCustomer(customerDataDto);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }
    /**
     * Endpoint to get all customers.
     *
     * @param token The authentication token.
     * @return ResponseEntity containing the response model and HTTP status.
     */
    @GetMapping("/get-all-customer")
    public ResponseEntity<ResponseModel> getAllCustomer(@RequestHeader ("Authorization")String token){
        ResponseModel responseModel = findcustomerService.getAllCustomer();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }
    /**
     * Endpoint to get a specific customer by ID.
     *
     * @param token The authentication token.
     * @param id    The ID of the customer to retrieve.
     * @return ResponseEntity containing the response model and HTTP status.
     */
    @GetMapping("/get-customer/{id}")
    public ResponseEntity<ResponseModel> getCustomer(@RequestHeader ("Authorization")String token,@PathVariable String id){
        ResponseModel responseModel = new ResponseModel();

        if(token.equals("")||token==null||!token.startsWith("Bearer")){
            responseModel.setMessage("No token found!");
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }

        if(jwtUtils.tokenExpired(token)){
            responseModel.setMessage("Session Expired!");
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }

        responseModel = findcustomerService.getCustomerById(id);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

}
