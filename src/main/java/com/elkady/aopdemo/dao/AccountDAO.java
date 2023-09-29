package com.elkady.aopdemo.dao;

import java.util.List;

import com.elkady.aopdemo.Account;

public interface AccountDAO {
    
    void addAccount(Boolean test);
    String getUsername();
    List<Account> findAccounts();
}
