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

import bao.webapp.model.Business;
import bao.webapp.model.Inventory;
import bao.webapp.model.User;
import bao.webapp.service.BusinessService;

@RestController
public class BusinessController {

	@Autowired
	private BusinessService businessService;

	@RequestMapping("/business/{id}")
	public List<Inventory> display(@PathVariable("id") Long id) {
		try {
			return businessService.findByBusinessID(id);
		} catch (Exception exp) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
		}
	}

	@RequestMapping(value = "/inventory", method = RequestMethod.POST)
	public Boolean createInventory(@RequestBody Inventory inventory) {
		businessService.create(inventory);

		return true;
	}
}