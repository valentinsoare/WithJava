package WithJava.BankingWithImmutability.src.main.java.playing.bankingwithimmutability;

import java.util.*;

final class BankCustomer {
    private static final Random rnd;

    static {
        rnd = new Random();
    }

    private String customerName;
    private final String customerID;
    private final List<BankAccount> accounts;

    private String generateID() {
        String word;
        StringBuilder toReturn = new StringBuilder();

        for (String e : customerName.split(",")) {
            word = e.trim().toUpperCase();
            toReturn.append(word.charAt(rnd.nextInt(word.length())));
        }

        word = String.valueOf(rnd.nextInt(10000001) + 1);

        return toReturn.append("0".repeat(15 - word.length())).append(word).toString();
    }

    public BankCustomer(String customerName) {
        this.customerName = customerName;
        this.customerID = generateID();
        this.accounts = new ArrayList<>();
    }

     BankCustomer(String customerName, double checkingInitialDeposit, double savingsInitialDeposit) {
        this.customerName = customerName;
        this.customerID = generateID();
        this.accounts = new ArrayList<>(Arrays.asList(
                new BankAccount(AccountType.CHECKING, checkingInitialDeposit),
                new BankAccount(AccountType.SAVINGS, savingsInitialDeposit)
        ));
    }

    public BankCustomer(BankCustomer c) {
        this.customerName = c.customerName;
        this.customerID = c.customerID;
        this.accounts = c.getAccounts();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public List<BankAccount> getAccounts() {
        return List.copyOf(accounts);
    }

    public boolean addAccount(BankAccount accountToBeWorkWith) {
        boolean isAlready = false;

        for (BankAccount account : accounts) {
            if ((account.getType().toString().equalsIgnoreCase(accountToBeWorkWith.getType().toString())) &&
                    account.getBalance().equals(accountToBeWorkWith.getBalance())) {
                isAlready = true;
                break;
            }
        }

        if (!isAlready) {
            accounts.add(accountToBeWorkWith);
            return true;
        }

        return false;
    }

    public BankAccount getAccount(AccountType type) {
        for (BankAccount b : accounts) {
            if (b.getType().equals(type)) {
                return b;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();

        String[] accountsAsStrings = new String[accounts.size()];
        Arrays.setAll(accountsAsStrings, i -> accounts.get(i).toString());

        toReturn.append(String.format("%nName: %s; Customer ID: %s; %nAccounts: %s",
                customerName, customerID, String.join(", ",accountsAsStrings)));

        toReturn.append(";\nTransactions");

        if (this.getAccount(AccountType.CHECKING).getTransactions().isEmpty()) {
            toReturn.append(String.format("%n%s","- no transactions for CHECKING account;"));
        }

        if (this.getAccount(AccountType.SAVINGS).getTransactions().isEmpty()) {
            toReturn.append(String.format("%n%s","- no transactions for SAVINGS account;"));
        }

        for (BankAccount account : accounts) {
            for (Map.Entry<Long, Transaction> transaction : this.getAccount(account.getType()).getTransactions().entrySet()) {
                toReturn.append(String.format("%n%s", transaction.getValue()));
            }
        }

        return toReturn.toString();
    }
}
