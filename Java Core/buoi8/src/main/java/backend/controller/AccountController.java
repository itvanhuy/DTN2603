package backend.controller;

import backend.service.IAccountService;
import backend.service.impl.AccountService;
import entity.Account;

import java.util.List;

public class AccountController {
    private final IAccountService accountService;

    public AccountController() {
        this.accountService = new AccountService();
    }

    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    public Account getAccountById(int id) {
        return accountService.getAccountById(id);
    }

    public boolean addAccount(Account account) {
        return accountService.addAccount(account);
    }

    public boolean updateAccount(Account account) {
        return accountService.updateAccount(account);
    }

    public boolean deleteAccount(int id) {
        return accountService.deleteAccount(id);
    }

    public void displayAccountsAsTable() {
        List<Account> accounts = getAllAccounts();

        if (accounts.isEmpty()) {
            System.out.println("Không có dữ liệu Account.");
            return;
        }

        System.out.println("\n" + "=".repeat(120));
        System.out.printf("| %-4s | %-25s | %-20s | %-25s | %-8s | %-8s | %-12s |%n",
                "ID", "Email", "Username", "Fullname", "Dept", "Pos", "Create Date");
        System.out.println("=".repeat(120));

        for (Account account : accounts) {
            System.out.println(account);
        }

        System.out.println("=".repeat(120));
    }
}
