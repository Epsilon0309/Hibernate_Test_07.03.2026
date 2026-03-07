package com;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import DAO.CustomerDAO;
import DAO.CustomerDAOImpl;
import exception.CheckingException;

public class App 
{
    public static void main( String[] args )
    {
        EntityManagerFactory emf  = Persistence.createEntityManagerFactory("Customer_Management_System");
        EntityManager em = emf.createEntityManager();

    	ApplicationContext context =
    	        new AnnotationConfigApplicationContext(CustomerDAOImpl.class);

    	CustomerDAOImpl CustomerDAO = context.getBean(CustomerDAOImpl.class);

    	CustomerDAO.em = em;

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n========== Customer Management System ==========");
            System.out.println("1. Add New Customer");
            System.out.println("2. View All Cutomers");
            System.out.println("3. Update Customer Email by Customer Mobile Number");
            System.out.println("4. Delete Customer by Customer Password");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
				try {
					CustomerDAO.addNewCustomer();
				} catch (CheckingException e) {
					System.out.println(e);;
				}
                    break;
                case 2:
                	CustomerDAO.viewAllCustomers();
                    break;
                case 3:
				try {
					CustomerDAO.updateCustomerEmailByUsingMobileNumber();
				} catch (CheckingException e) {
					System.out.println(e);;
				}
                    break;
                case 4:
				try {
					CustomerDAO.deleteCustomerByUsingPassword();
				} catch (CheckingException e) {
					System.out.println(e);;
				}
                case 0:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }

        } while (choice != 0);

        sc.close();
        em.close();
    }
}
