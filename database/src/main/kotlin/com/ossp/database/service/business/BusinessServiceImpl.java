package com.ossp.database.service.business;

import com.ossp.database.model.Inventory;
import com.ossp.database.repository.BusinessRepository;
import com.ossp.database.repository.InventoryRepository;
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

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public void create(Inventory inventory) {
        this.inventoryRepository.save(inventory);
    }

    @Override
    public List<Inventory> findByBusinessID(long businessID) {
        List<Inventory> all = new ArrayList<>();

        for (Inventory inventory : this.inventoryRepository.findByBusiness(businessID))
            all.add(inventory);

        return all;
    }
}
