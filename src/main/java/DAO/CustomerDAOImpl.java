package DAO;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import exception.CheckingException;
import model.CustomerInformation;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@PersistenceContext
	public
	EntityManager em;
	public Scanner sc = new Scanner(System.in);

	public static boolean isValidPassword(String password) {

		if (password.length() < 4) {
			return false;
		}

		boolean hasUpper = false;
		boolean hasLower = false;
		boolean hasDigit = false;
		boolean hasSpecial = false;

		for (int i = 0; i < password.length(); i++) {
			char ch = password.charAt(i);

			if (Character.isUpperCase(ch)) {
				hasUpper = true;
			} else if (Character.isLowerCase(ch)) {
				hasLower = true;
			} else if (Character.isDigit(ch)) {
				hasDigit = true;
			} else {
				hasSpecial = true;
			}
		}

		return hasUpper && hasLower && hasDigit && hasSpecial;
	}

	@Override
	public void addNewCustomer() throws CheckingException {

		EntityTransaction et = em.getTransaction();
		et.begin();

		CustomerInformation customer = new CustomerInformation();

		System.out.println("Enter the name: ");
		customer.setName(sc.nextLine());

		System.out.println("Enter the email: ");
		String email = sc.next();
		if (email.endsWith("@gmail.com")) {
			customer.setEmail(email);
		} else {
			throw new CheckingException("Email must contain '@gmail.com' at last.....");
		}

		System.out.println("Enter the password: ");
		String pw = sc.next();
		if (isValidPassword(pw)) {
			customer.setPassword(pw);
		} else {
			throw new CheckingException(
					"Password must contain one uppercae,one lowercae, one digit, one special character and must be of length 4......");
		}

		System.out.println("Enter the mobile number: ");
		long mobileNumber = sc.nextLong();
		String mn = mobileNumber + "";
		if (mn.length() == 10
				&& (mn.charAt(0) == '6' || mn.charAt(0) == '7' || mn.charAt(0) == '8' || mn.charAt(0) == '9')) {
			customer.setMobileNumber(mobileNumber);
		} else {
			throw new CheckingException("Mobile Number must contain 10 digits and should starts with 6,7,8 or 9.....");
		}

		em.persist(customer);

		et.commit();

		System.out.println("Data inserted");
	}

	@Override
	public void viewAllCustomers() {

		EntityTransaction et = em.getTransaction();
		et.begin();

		String view = "Select customer from CustomerInformation customer";

		Query query = em.createQuery(view);

		List<CustomerInformation> allContent = query.getResultList();

		for (CustomerInformation c : allContent) {
			System.out.print("Customer Id: " + c.getId() + " ||");
			System.out.print("Customer Name: " + c.getName() + " ||");
			System.out.print("Customer Email: " + c.getEmail() + " ||");
			System.out.print("Customer Password: " + c.getPassword() + " ||");
			System.out.print("Customer Mobile Number: " + c.getMobileNumber() + " ||");
			System.out.println();
		}

		et.commit();

	}

	@Override
	public void updateCustomerEmailByUsingMobileNumber() throws CheckingException {

		EntityTransaction et = em.getTransaction();
		et.begin();

		String update = "Update CustomerInformation customer set customer.email = ?1 where customer.mobileNumber = ?2";

		Query query = em.createQuery(update);

		System.out.println("Enter mobile number:");
		long mobileNumber = sc.nextLong();
		String mn = mobileNumber + "";
		if (mn.length() == 10
				&& (mn.charAt(0) == '6' || mn.charAt(0) == '7' || mn.charAt(0) == '8' || mn.charAt(0) == '9')) {
			query.setParameter(2, mobileNumber);
		} else {
			throw new CheckingException("Mobile Number must contain 10 digits and should starts with 6,7,8 or 9.....");
		}

		System.out.println("Enter email:");
		String email = sc.next();
		if (email.endsWith("@gmail.com")) {
			query.setParameter(1, email);
		} else {
			throw new CheckingException("Email must contain '@gmail.com' at last.....");
		}

		int result = query.executeUpdate();

		if (result != 0) {
			System.out.println("Data updated.....");
		} else {
			System.out.println("No data found.....");
		}
		
		et.commit();
	}

	@Override
	public void deleteCustomerByUsingPassword() throws CheckingException {

		EntityTransaction et = em.getTransaction();
		et.begin();

		String delete = "DELETE FROM CustomerInformation c WHERE c.password = ?1";
		
		System.out.println("Enter the password to delete: ");
		String password = sc.next();


			if (isValidPassword(password)) {

				Query query = em.createQuery(delete);
				query.setParameter(1, password);

				int result = query.executeUpdate();

				if (result > 0) {
					System.out.println("Customer deleted successfully...");
				} else {
					System.out.println("No customer found with this password...");
				}

			} else {
				throw new CheckingException(
						"Password must contain one uppercase, one lowercase, one digit, one special character and length >= 4");
			}

	}

}
