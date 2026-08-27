import java.time.LocalDateTime;

public class Exercise2 {

    static class Account {
        String email;
        String username;
        String fullName;
        LocalDateTime createDate;

        public Account() {
            this.email = "Email 1";
            this.username = "User name 1";
            this.fullName = "Full name 1";
            this.createDate = LocalDateTime.now();
        }
    }

    public static void question1() {
        Account[] accounts = new Account[5];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account();
            accounts[i].email = "Email " + (i + 1);
            accounts[i].username = "User name " + (i + 1);
            accounts[i].fullName = "Full name " + (i + 1);
        }

        System.out.println("Exercise 2 - Question 1:");
        for (int i = 0; i < accounts.length; i++) {
            Account acc = accounts[i];
            System.out.printf("Account %d: %s, %s, %s, %s%n", i + 1, acc.email, acc.username, acc.fullName, acc.createDate);
        }
        System.out.println();
    }
}