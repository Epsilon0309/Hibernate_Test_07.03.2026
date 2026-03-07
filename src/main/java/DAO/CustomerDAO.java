package DAO;

import exception.CheckingException;

public interface CustomerDAO {

	void addNewCustomer() throws CheckingException;
	
	void viewAllCustomers();
	
	void updateCustomerEmailByUsingMobileNumber() throws CheckingException;
	
	void deleteCustomerByUsingPassword()throws CheckingException;
	
}
