package bao.webapp.service;

import bao.webapp.model.Business;
import bao.webapp.model.Inventory;
import java.util.List;

public interface BusinessService {

	public List<Inventory> findByBusinessID(long businessID);

	public void create(Inventory inventory);
}
