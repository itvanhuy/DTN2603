package backend;

import entity.Account;
import java.util.List;

public interface IQLAccount {
    List<Account> getAllAccounts();
    Account getAccountById(int id);
    boolean addAccount(Account account);
    boolean updateAccount(Account account);
    boolean deleteAccount(int id);
    void displayAccountsAsTable();
}