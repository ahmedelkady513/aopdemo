package com.elkady.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.elkady.aopdemo.Account;

@Repository
public class AccountDAOImpl implements AccountDAO {
    private String username = "Ahmed";
    @Override
    public void addAccount(Boolean test) {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    public String getUsername() {
        System.out.println(username);
        return username;
    }

    @Override
    public List<Account> findAccounts() {
        List<Account> accounts = new ArrayList<>();

        Account temp = new Account("Ahmed","silver");
        Account temp2 = new Account("Mohamed","Gold");

        accounts.add(temp);
        accounts.add(temp2);
        
        return accounts;
    }


    
}
