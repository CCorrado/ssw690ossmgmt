package com.ossp.database.service.business;

import com.ossp.database.model.Business;

import java.util.List;

public interface BusinessService {

    List<Business> findByBusinessID(long businessID);

    Business create(Business inventory);
}
