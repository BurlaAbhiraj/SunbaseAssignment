package com.sunbase.CustomerApp.controller;

import com.sunbase.CustomerApp.model.CustomerLoginDetailsDto;
import com.sunbase.CustomerApp.model.CustomerRegistrationDetailsDto;
import com.sunbase.CustomerApp.model.ResponseModel;
import com.sunbase.CustomerApp.service.CustomerLoginService;
import com.sunbase.CustomerApp.service.CustomerRegistrationService;
import com.sunbase.CustomerApp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * Controller class for handling customer login and registration operations in the CustomerApp.
 * The class defines endpoints for user login and customer registration.
 */
@Controller
@RequestMapping("/customer-login")
public class CustomerLoginController {
    @Autowired
    private CustomerLoginService customerLoginService;
    @Autowired
    private CustomerService findcustomerService;
    @Autowired
    private CustomerRegistrationService customerRegistrationService;


    /**
     * Login controller endpoint.
     * Uses a remote API to generate a token for user authentication.
     * Returns a ModelAndView with the generated token and customer list.
     *
     * @param customerLoginDetailsDto The customer login details.
     * @return ModelAndView containing the generated token and customer list.
     */
    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute CustomerLoginDetailsDto customerLoginDetailsDto) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(customerLoginDetailsDto);
        String token = customerLoginService.apiCall(customerLoginDetailsDto);
        ResponseModel customers = findcustomerService.getAllCustomer();
        modelAndView.addObject("token", token);
        modelAndView.addObject("customerList", customers.getData().getCustomerDetailsList());
        modelAndView.setViewName("Dashboard");
        return modelAndView;
    }

    /**
     * Register customer controller endpoint.
     * Saves customer data from the add customer page.
     * Returns a ModelAndView with a response message.
     *
     * @param customerRegistrationDetailsDto The customer registration details.
     * @return ModelAndView containing the response message.
     */
    @PostMapping("/register-customer")
    public ModelAndView registerCustomer(@ModelAttribute CustomerRegistrationDetailsDto customerRegistrationDetailsDto) {
        ModelAndView modelAndView = new ModelAndView();
        ResponseModel responseModel = customerRegistrationService.registerCustomer(customerRegistrationDetailsDto);
        System.out.println(responseModel.getMessage());
        modelAndView.addObject("message", responseModel.getMessage());
        modelAndView.setViewName("AddCustomer");
        System.out.println("ModelAndView : " + modelAndView);
        return modelAndView;
    }

}
