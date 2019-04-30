package bao.webapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bao.webapp.model.Inventory;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long> {

	List<Inventory> findByBusiness(long businessId);
}
