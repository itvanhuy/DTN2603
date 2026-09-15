package backend.service;

import entity.Account;

import java.util.List;

public interface IAccountService {
    List<Account> getAllAccounts();
    Account getAccountById(int id);
    boolean addAccount(Account account);
    boolean updateAccount(Account account);
    boolean deleteAccount(int id);
}
