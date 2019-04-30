package bao.webapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bao.webapp.model.Business;
import bao.webapp.model.Inventory;
import bao.webapp.repository.BusinessRepository;
import bao.webapp.repository.InventoryRepository;

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
