package backend.service.impl;

import backend.service.IAccountService;
import backend.repository.impl.AccountRepository;
import entity.Account;
import entity.Department;
import entity.Position;

import java.util.List;

public class AccountService implements IAccountService {
    private final AccountRepository accountRepository;

    public AccountService() {
        this.accountRepository = new AccountRepository();
    }

    public List<Account> getAllAccounts() {
        return accountRepository.getAll();
    }

    public Account getAccountById(int id) {
        if (id <= 0) {
            return null;
        }
        return accountRepository.getById(id);
    }

    public boolean addAccount(Account account) {
        if (!isValid(account)) {
            return false;
        }
        return accountRepository.add(account);
    }

    public boolean updateAccount(Account account) {
        if (account == null || account.getId() <= 0 || !isValid(account)) {
            return false;
        }
        return accountRepository.update(account);
    }

    public boolean deleteAccount(int id) {
        if (id <= 0) {
            return false;
        }
        return accountRepository.delete(id);
    }

    private boolean isValid(Account account) {
        if (account == null) {
            return false;
        }

        if (account.getEmail() == null || account.getEmail().trim().isEmpty()) {
            return false;
        }

        if (account.getUsername() == null || account.getUsername().trim().isEmpty()) {
            return false;
        }

        if (account.getFullName() == null || account.getFullName().trim().isEmpty()) {
            return false;
        }

        Department department = account.getDepartment();
        Position position = account.getPosition();

        if (department == null || department.getId() <= 0) {
            return false;
        }

        if (position == null || position.getId() <= 0) {
            return false;
        }

        return true;
    }
}
