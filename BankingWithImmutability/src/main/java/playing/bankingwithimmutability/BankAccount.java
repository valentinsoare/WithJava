package playing.bankingwithimmutability;

import java.util.*;

public class BankAccount {
    private static final Random rnd;
    private static final List<String> accountsIdRegistered;

    static {
        rnd = new Random();
        accountsIdRegistered = new ArrayList<>();
    }

    private final AccountType type;
    private Double balance;
    private final String accountID;
    private final Map<Long, Transaction> transactions;

    private String generateIdAccount() {
        boolean exists = true;
        String toReturn = "none";

        while (exists) {
            toReturn = String.valueOf(rnd.nextInt(999_999) + 1_000_000);
            if (!accountsIdRegistered.contains(toReturn)) exists = false;
        }

        String toAdd = "BR" + toReturn;
        accountsIdRegistered.add(toAdd);

        return toAdd;
    }

    public BankAccount(AccountType type, Double balance) {
        this.type = type;
        this.balance = balance;
        this.accountID = generateIdAccount();
        this.transactions = new LinkedHashMap<>();
    }

    public BankAccount(BankAccount a) {
        this.type = a.type;
        this.balance = a.balance;
        this.accountID = a.accountID;
        this.transactions = Map.copyOf(a.transactions);
    }

    public AccountType getType() {
        return type;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getAccountID() {
        return accountID;
    }

    public String lastIdRegistered() {
        return accountsIdRegistered.get(accountsIdRegistered.size() - 1);
    }

    public static List<String> getAccountsRegisteredIDs() {
        return List.copyOf(accountsIdRegistered);
    }

    public Map<Long, Transaction> getTransactions() {
        return Map.copyOf(transactions);
    }

    boolean commitTransaction(int routingNumber, long transactionId, String customerId, double amount) {
        if ((this.balance + amount) < 0) {
            System.out.printf("%n%s", "Not enough founds for this transaction!");
            return false;
        }

        this.balance += amount;
        transactions.put(transactionId,
                new Transaction(routingNumber, customerId, transactionId, amount));

        return true;
    }

    @Override
    public String toString() {
        return String.format("%s, %.2f, %s", type, balance, accountID);
    }
}
