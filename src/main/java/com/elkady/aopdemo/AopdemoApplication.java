package com.elkady.aopdemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.elkady.aopdemo.dao.AccountDAO;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO) {
		return runner -> {
			// demoTheBeforeAdvice(theAccountDAO);
			// demoTheAfterReturningAdvice(theAccountDAO);
			// demoTheAfterThrowingAdvice(theAccountDAO);

			demotheAfterAdvice(theAccountDAO);
		};
	}

	private void demotheAfterAdvice(AccountDAO theAccountDAO) {
		List<Account> accounts = null;

		try {

			// add a boolean flag to simulate exceptions
			boolean tripWire = false;
			accounts = theAccountDAO.findAccounts(tripWire);
		} catch (Exception ex) {
			System.out.println("\n\nMain Program: .. caught Exception: " + ex);
		}

		System.out.println("\n\nMain Program:  demoTheAfterThrowingAdvice");
		System.out.println("----");
		System.out.println(accounts);
		System.out.println("\n");
	}

	private void demoTheAfterThrowingAdvice(AccountDAO theAccountDAO) {

		List<Account> accounts = null;

		try {

			// add a boolean flag to simulate exceptions
			boolean tripWire = true;
			accounts = theAccountDAO.findAccounts(tripWire);
		} catch (Exception ex) {
			System.out.println("\n\nMain Program: .. caught Exception: " + ex);
		}

		System.out.println("\n\nMain Program:  demoTheAfterThrowingAdvice");
		System.out.println("----");

		System.out.println(accounts);

		System.out.println("\n");
	}

	private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {
		List<Account> accounts = theAccountDAO.findAccounts();

		System.out.println("\n\nMain Program:  demoTheAfterReturningAdvice");
		System.out.println("----");

		System.out.println(accounts);

		System.out.println("\n");
	}

	private void demoTheBeforeAdvice(AccountDAO theAccountDAO) {
		theAccountDAO.addAccount(false);
		theAccountDAO.getUsername();
	}
}
