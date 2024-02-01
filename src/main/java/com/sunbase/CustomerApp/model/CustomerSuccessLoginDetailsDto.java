package com.sunbase.CustomerApp.model;

import lombok.Data;
/**
 * DTO (Data Transfer Object) class representing the details of a successful customer login.
 */
@Data
public class CustomerSuccessLoginDetailsDto {

    private String token;// Token generated upon successful customer login.

}
