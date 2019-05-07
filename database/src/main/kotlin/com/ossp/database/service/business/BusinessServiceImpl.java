package com.ossp.database.service.business;

import com.ossp.database.model.Business;
import com.ossp.database.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    @Override
    public Business create(Business business) {
        return this.businessRepository.save(business);
    }

    @Override
    public List<Business> findByBusinessID(long businessID) {
        List<Business> all = new ArrayList<>();

        for (Business business : this.businessRepository.findAll()) {
            all.add(business);
        }

        return all;
    }
}
