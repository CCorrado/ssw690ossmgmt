package bao.webapp;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import bao.webapp.model.OrderStatus;
import bao.webapp.model.User;
import bao.webapp.model.AuthInfo;
import bao.webapp.model.Order;
import bao.webapp.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public Iterable<Order> list() {
		return orderService.all();
	}
	
	@RequestMapping(value = "/orders", method = RequestMethod.POST)
	public Boolean createOrder(@RequestBody Order order) {
		orderService.create(order);

		return true;
	}

	@RequestMapping(value = "/orders-delete/{orderID}", method = RequestMethod.POST)
	public Boolean deleteOrder(@PathVariable("orderID") Long orderID) {
		orderService.delete(orderService.findById(orderID));

		return true;
	}

	@RequestMapping("/orders/status/{orderID}")
	public OrderStatus getByOrderID(@PathVariable("orderID") Long orderID) {
		Order order = orderService.findById(orderID);

		if (order == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
		} else {
			OrderStatus orderStatus = new OrderStatus();
			orderStatus.userid = order.getUserID();
			orderStatus.orderid = order.getOrderID();
			orderStatus.status = order.getStatus();

			return orderStatus;
		}
	}

	@RequestMapping("/orders/{userID}")
	public List<Order> list(@PathVariable("userID") Long userID) {
		try {
			return orderService.findByUserID(userID);
		} catch (Exception exp) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
		}
	}
}
