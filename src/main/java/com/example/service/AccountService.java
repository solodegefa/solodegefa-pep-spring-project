package com.example.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
@Service
@Transactional
public class AccountService {
   
    public AccountRepository accountRepository;
@Autowired
  public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
public Account createAccount(Account account){

        if ((account.getUsername().isBlank() || account.getPassword().length() < 4)){
            return null;
        }
        return accountRepository.save(account);
    }
    public Account findByUsername(String username){
        return accountRepository.findAccountByUsername(username);
    }
    public Account loginAccount(Account account) {
        Account loginAccount = accountRepository.findAccountByUsername(account.getUsername());
        
       // return accountRepository.findAccountByUsername(account.getUsername());
            if(loginAccount == null || !loginAccount.getPassword().equals(account.getPassword())){ 
                return null;
            }
            return loginAccount;
        }
    

}

