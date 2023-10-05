package com.elkady.aopdemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.elkady.aopdemo.dao.AccountDAO;
import com.elkady.aopdemo.service.TrafficFortuneService;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO,
			TrafficFortuneService theTrafficFortuneService) {
		return runner -> {
			// demoTheBeforeAdvice(theAccountDAO);
			// demoTheAfterReturningAdvice(theAccountDAO);
			// demoTheAfterThrowingAdvice(theAccountDAO);
			// demotheAfterAdvice(theAccountDAO);
			// demoTheAroundAdvice(thTrafficFortuneService);
			// demoTheAroundAdviceHandleException(theTrafficFortuneService);

			demotheAroundAdviceRethrowException(theTrafficFortuneService);
		};
	}

	private void demotheAroundAdviceRethrowException(TrafficFortuneService theTrafficFortuneService) {
		
		System.out.println("\nMain Program: demotheAroundAdviceRethrowException");

		System.out.println("\nCalling getFortune()");

		boolean tripWire = true;
		String data = theTrafficFortuneService.getFortune(tripWire);

		System.out.println("\nMy Fortune is: " + data);

		System.out.println("Finished");
	}

	private void demoTheAroundAdviceHandleException(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("\nMain Program: demoTheAroundAdviceHandleException");

		System.out.println("\nCalling getFortune()");

		boolean tripWire = true;
		String data = theTrafficFortuneService.getFortune(tripWire);

		System.out.println("\nMy Fortune is: " + data);

		System.out.println("Finished");
	}

	private void demoTheAroundAdvice(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("\nMain Program: demotheAroundAdvice");

		System.out.println("\nCalling getFortune()");

		String data = theTrafficFortuneService.getFortune();

		System.out.println("\nMy Fortune is: " + data);

		System.out.println("Finished");
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
