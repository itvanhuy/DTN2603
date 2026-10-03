package backend.repository;

import entity.Account;
import java.util.List;

public interface IAccountRepository {
    List<Account> getAll();
    Account getById(int id);
    boolean add(Account account);
    boolean update(Account account);
    boolean delete(int id);
}
