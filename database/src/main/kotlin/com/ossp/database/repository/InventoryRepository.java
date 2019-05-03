package com.ossp.database.repository;

import com.ossp.database.model.Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long> {

	List<Inventory> findByBusiness(long businessId);
}
