package backend.service.impl;

import backend.service.IAccountService;
import backend.repository.impl.AccountRepository;
import entity.Account;
import entity.Department;
import entity.Position;

import java.util.List;
import java.util.regex.Pattern;

public class AccountService implements IAccountService {
    private static final int MIN_LENGTH = 6;
    private static final int MAX_LENGTH = 100;
    private static final Pattern GMAIL_PATTERN = Pattern.compile("(?i)^[a-z0-9._%+-]+@gmail\\.com$");

    private final AccountRepository accountRepository;

    public AccountService() {
        this.accountRepository = new AccountRepository();
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.getAll();
    }

    @Override
    public Account getAccountById(int id) {
        if (id <= 0) {
            return null;
        }
        return accountRepository.getById(id);
    }

    @Override
    public boolean addAccount(Account account) {
        if (!isValid(account)) {
            return false;
        }
        if (isDuplicateUsername(account.getUsername(), account.getId())) {
            return false;
        }
        if (isDuplicateEmail(account.getEmail(), account.getId())) {
            return false;
        }
        if (!departmentExists(account.getDepartment().getId())) {
            return false;
        }
        if (!positionExists(account.getPosition().getId())) {
            return false;
        }
        return accountRepository.add(account);
    }

    @Override
    public boolean updateAccount(Account account) {
        if (account == null || account.getId() <= 0 || !isValid(account)) {
            return false;
        }
        if (isDuplicateUsername(account.getUsername(), account.getId())) {
            return false;
        }
        if (isDuplicateEmail(account.getEmail(), account.getId())) {
            return false;
        }
        if (!departmentExists(account.getDepartment().getId())) {
            return false;
        }
        if (!positionExists(account.getPosition().getId())) {
            return false;
        }
        if (accountRepository.getById(account.getId()) == null) {
            return false;
        }
        return accountRepository.update(account);
    }

    @Override
    public boolean deleteAccount(int id) {
        if (id <= 0 || accountRepository.getById(id) == null) {
            return false;
        }
        return accountRepository.delete(id);
    }

    private boolean isValid(Account account) {
        if (account == null) {
            return false;
        }

        if (!isValidUsername(account.getUsername())) {
            return false;
        }

        if (!isValidEmail(account.getEmail())) {
            return false;
        }

        if (!isValidFullName(account.getFullName())) {
            return false;
        }

        Department department = account.getDepartment();
        Position position = account.getPosition();

        if (department == null || !isValidDepartmentId(department.getId())) {
            return false;
        }

        if (position == null || !isValidPositionId(position.getId())) {
            return false;
        }

        return true;
    }

    private boolean isValidUsername(String username) {
        if (username == null) {
            return false;
        }
        String trimmed = username.trim();
        return trimmed.length() > MIN_LENGTH && trimmed.length() < MAX_LENGTH;
    }

    private boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        String trimmed = email.trim();
        return trimmed.length() > MIN_LENGTH
                && trimmed.length() < MAX_LENGTH
                && GMAIL_PATTERN.matcher(trimmed).matches();
    }

    private boolean isValidFullName(String fullName) {
        if (fullName == null) {
            return false;
        }
        String trimmed = fullName.trim();
        return trimmed.length() > MIN_LENGTH && trimmed.length() < MAX_LENGTH;
    }

    private boolean isValidDepartmentId(int departmentId) {
        return departmentId > 0;
    }

    private boolean isValidPositionId(int positionId) {
        return positionId > 0;
    }

    private boolean isDuplicateUsername(String username, int ignoreId) {
        if (username == null) {
            return true;
        }
        String target = username.trim();
        List<Account> accounts = accountRepository.getAll();
        for (Account account : accounts) {
            if (account != null
                    && account.getUsername() != null
                    && account.getUsername().trim().equalsIgnoreCase(target)
                    && account.getId() != ignoreId) {
                return true;
            }
        }
        return false;
    }

    private boolean isDuplicateEmail(String email, int ignoreId) {
        if (email == null) {
            return true;
        }
        String target = email.trim();
        List<Account> accounts = accountRepository.getAll();
        for (Account account : accounts) {
            if (account != null
                    && account.getEmail() != null
                    && account.getEmail().trim().equalsIgnoreCase(target)
                    && account.getId() != ignoreId) {
                return true;
            }
        }
        return false;
    }

    private boolean departmentExists(int departmentId) {
        return departmentId > 0 && new DepartmentService().getDepartmentById(departmentId) != null;
    }

    private boolean positionExists(int positionId) {
        return positionId > 0 && new PositionService().getPositionById(positionId) != null;
    }
}
