package com.ossp.database.service.business;

import com.ossp.database.model.Inventory;

import java.util.List;

public interface BusinessService {

    List<Inventory> findByBusinessID(long businessID);

    void create(Inventory inventory);
}
