package com.ossp.database.controller;

import com.ossp.database.error.ObjectNotFound;
import com.ossp.database.model.Inventory;
import com.ossp.database.service.business.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @RequestMapping("/business/{id}")
    public List<Inventory> display(@PathVariable("id") Long id) {
        try {
            return businessService.findByBusinessID(id);
        } catch (Exception exp) {
            throw new ObjectNotFound(HttpStatus.NOT_FOUND, "Business Not Found");
        }
    }

    @RequestMapping(value = "/inventory", method = RequestMethod.POST)
    public Boolean createInventory(@RequestBody Inventory inventory) {
        businessService.create(inventory);

        return true;
    }
}