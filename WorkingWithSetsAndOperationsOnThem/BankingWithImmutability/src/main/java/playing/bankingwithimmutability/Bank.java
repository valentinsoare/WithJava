package playing.bankingwithimmutability;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

public class Bank {
    private final int routingNumber;
    private long lastTransactionId;
    private final Map<String, BankCustomer> customers;

    public Bank(int routingNumber) {
        this.routingNumber = routingNumber;
        this.customers = new HashMap<>();
        this.lastTransactionId = 1;
    }

    public BankCustomer getCustomerById(String id) {
        if (id.isBlank()) {
            return null;
        }

        if (customers.containsKey(id)) {
            return customers.get(id);
        }

        return null;
    }

    public BankCustomer getCustomerByName(String name) {
        if (name.isBlank()) {
            return null;
        }

        for (Map.Entry<String, BankCustomer> customer : customers.entrySet()) {
            if (name.equalsIgnoreCase(customer.getValue().getCustomerName())) {
                return customer.getValue();
            }
        }

        return null;
    }

    public boolean addCustomer(String name, double checkingInitialDeposit, double savingsInitialDeposit) {
        for (Map.Entry<String, BankCustomer> customer : customers.entrySet()) {
            if (name.equalsIgnoreCase(customer.getValue().getCustomerName())) return false;
        }

        boolean alreadyExists = true;
        BankCustomer newCustomer;

        while (alreadyExists) {
            newCustomer = new BankCustomer(name, checkingInitialDeposit, savingsInitialDeposit);

            if (!customers.containsKey(newCustomer.getCustomerID())) {
                customers.put(newCustomer.getCustomerID(), newCustomer);
                alreadyExists = false;
            }
        }

        return true;
    }

    public boolean doTransaction(String id, AccountType type, double amount) {
        BankCustomer customer = customers.get(id);
        if (customer != null) {
            Objects.requireNonNull(customer.getAccount(type)).commitTransaction(routingNumber, lastTransactionId++,
                    id, amount);
            return true;
        }

        System.out.printf("%n%s", "Invalid customer id!");
        return false;
    }

    public long getRoutingNumber() {
        return routingNumber;
    }

    public long getLastTransactionId() {
        return lastTransactionId;
    }

    public Map<String, BankCustomer> getCustomers() {
        return Map.copyOf(customers);
    }
}
