package bao.webapp.service;

import bao.webapp.model.Order;
import java.util.List;

public interface OrderService {

	public void create(Order order);

	public void delete(Order order);

	public Order findById(long id);

	public List<Order> findByUserID(long userID);

	public Iterable<Order> all();
}
