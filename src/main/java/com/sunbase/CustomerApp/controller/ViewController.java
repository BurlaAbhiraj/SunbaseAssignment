package com.sunbase.CustomerApp.controller;


import com.sunbase.CustomerApp.model.ResponseModel;
import com.sunbase.CustomerApp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
/**
 * Controller class for handling view-related operations in the CustomerApp.
 * The class defines endpoints for login page, add customer page, and customer dashboard.
 */
@Controller
public class ViewController {
    @Autowired
    private CustomerService findcustomerService;
    /**
     * Endpoint for displaying the login page.
     *
     * @return ModelAndView for the Login page.
     */
    @GetMapping("/")
    public ModelAndView loginPage() {
        return new ModelAndView("Login");
    }
    /**
     * Endpoint for displaying the add customer page.
     *
     * @return ModelAndView for the AddCustomer page.
     */
    @GetMapping("/add-customer")
    public ModelAndView addCustomerPage() {
        return new ModelAndView("AddCustomer");
    }
    /**
     * Endpoint for displaying the customer dashboard.
     * Retrieves the list of customers and displays them on the dashboard.
     *
     * @param token The authentication token.
     * @return ModelAndView for the Dashboard page with the token and customer list.
     */
    @GetMapping("/dashboard/{token}")
    public ModelAndView customerDashboard(@PathVariable String token) {
        ModelAndView modelAndView = new ModelAndView();
        ResponseModel responseModel = findcustomerService.getAllCustomer();
        modelAndView.setViewName("Dashboard");
        modelAndView.addObject("token", token);
        modelAndView.addObject("customerList", responseModel.getData().getCustomerDetailsList());

        return modelAndView;
    }
}
