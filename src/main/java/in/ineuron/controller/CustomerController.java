package in.ineuron.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ineuron.model.Customer;
import in.ineuron.service.ICustomerService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ab")
public class CustomerController {
	
	@Autowired
	private ICustomerService service;

	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		return service.findAllCustomers();
	}
	
	@PostMapping("/customers")
	public Customer createCustomer(@RequestBody Customer customer) {
		return service.saveCustomer(customer);
	}
	
	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
		Customer customer = service.findCustomerById(id);
		return ResponseEntity.ok(customer);
	}
	
	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
		Customer cusDb = service.findCustomerById(id);

		cusDb.setFirstName(customer.getFirstName());
		cusDb.setLastName(customer.getLastName());
		cusDb.setEmailId(customer.getEmailId());

		Customer updateCustomer = service.saveCustomer(cusDb);
		return ResponseEntity.ok(updateCustomer);

	}
	
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
		service.deleteCustomerById(id);
		HashMap<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
