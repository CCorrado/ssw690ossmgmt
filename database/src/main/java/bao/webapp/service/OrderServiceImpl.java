package bao.webapp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bao.webapp.model.Order;
import bao.webapp.model.User;
import bao.webapp.repository.OrderRepository;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void create(Order order) {
		this.orderRepository.save(order);
	}

	@Override
	public void delete(Order order) {
		this.orderRepository.delete(order);
	}

	@Override
	public Order findById(long id) {
		return this.orderRepository.findById(id).get();
	}

	@Override
	public List<Order> findByUserID(long userID) {
		List<Order> all = new ArrayList<>();

		for (Order order : this.orderRepository.findByUserID(userID))
			all.add(order);

		return all;
	}

	@Override
	public Iterable<Order> all() {
		return this.orderRepository.findAll();
	}

}
