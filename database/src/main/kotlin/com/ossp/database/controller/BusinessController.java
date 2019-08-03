package com.ossp.database.controller;

import com.ossp.database.error.ObjectNotFound;
import com.ossp.database.model.Business;
import com.ossp.database.service.business.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @RequestMapping(path = "/business/{id}", method = RequestMethod.GET)
    public List<Business> display(@PathVariable("id") Long id) {
        try {
            return businessService.findByBusinessID(id);
        } catch (Exception exp) {
            throw new ObjectNotFound(HttpStatus.NOT_FOUND, "Business Not Found");
        }
    }

    @RequestMapping(path = "/business/create", method = RequestMethod.POST)
    public Business createBusiness(@RequestBody Business business) {
        return businessService.create(business);
    }
}