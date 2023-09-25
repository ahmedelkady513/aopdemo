package com.elkady.aopdemo.dao;

import org.springframework.stereotype.Repository;

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


    
}
