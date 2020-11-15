import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class AccountWarehouse {

    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    public AccountWarehouse() {
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    public String getNewAccountNumber() {
        //inits
        String accountNumber;
        Random rand = new Random();
        int length = 9;
        boolean nonUnique;

        //continue looping until a unique account number is generated
        do {
            //generate account number
            accountNumber = "";
            for(int i = 0; i < length; i++) {
                accountNumber += ((Integer)rand.nextInt(10));
            }

            //make sure uuid is actually unique
            nonUnique = false;
            for(Account a : this.accounts) {
                if(accountNumber.compareTo(a.getAccountNumber()) == 0) {
                    nonUnique = true;
                    break;
                }
            }

        } while (nonUnique);

        return accountNumber;
    }

    public void removeAccount(User accountHolder, String accountNumber) {
        for(Account a : accounts) {
            if(a.getAccountNumber().equals(accountNumber)) {
                accounts.remove(a);
                accountHolder.removeAccountFromUserList(a);
            }
        }
    }

    public Savings createSavingsAccount(User accountHolder, double startingDeposit) {
        ArrayList<Transaction> transactionHistory = new ArrayList<Transaction>();
        Savings newAccount = new Savings(startingDeposit, accountHolder, getNewAccountNumber());
        accounts.add(newAccount);
        accountHolder.addAccount(newAccount);
        return newAccount;
    }

    public Checking createCheckingAccount(User accountHolder, double startingDeposit) {
        ArrayList<Transaction> transactionHistory = new ArrayList<Transaction>();
        Checking newAccount = new Checking(startingDeposit, accountHolder, getNewAccountNumber());
        accounts.add(newAccount);
        accountHolder.addAccount(newAccount);
        return newAccount;
    }

    public Investment createInvestmentAccount(User accountHolder, double startingDeposit) {
        ArrayList<Transaction> transactionHistory = new ArrayList<Transaction>();
        Investment newAccount = new Investment(startingDeposit, accountHolder, getNewAccountNumber());
        accounts.add(newAccount);
        accountHolder.addAccount(newAccount);
        return newAccount;
    }

}
