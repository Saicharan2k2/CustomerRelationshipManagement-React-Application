package in.ineuron.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.dao.ICustomerRepository;
import in.ineuron.exception.CustomerNotFoundException;
import in.ineuron.model.Customer;

@Service("service")
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerRepository repo;
	
	@Override
	public List<Customer> findAllCustomers() {
		return (List<Customer>) repo.findAll();
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		return repo.save(customer);
	}

	@Override
	public Customer findCustomerById(Integer id) {
		Customer customer = repo.findById(id)
				.orElseThrow(()-> new CustomerNotFoundException("Record not found with the id : "+id));
		return customer;
	}

	@Override
	public void deleteCustomerById(Integer id) {
		Customer customer = repo.findById(id).
		orElseThrow(()-> new CustomerNotFoundException("Record not exist with the id : "+id));
		
		repo.delete(customer);
	}

}
