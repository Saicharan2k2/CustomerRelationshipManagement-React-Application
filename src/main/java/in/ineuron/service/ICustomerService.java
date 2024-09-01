package in.ineuron.service;

import java.util.List;

import in.ineuron.model.Customer;

public interface ICustomerService {

	public List<Customer> findAllCustomers();
	public Customer saveCustomer(Customer customer);
	public Customer findCustomerById(Integer id);
	public void deleteCustomerById(Integer id);
}
