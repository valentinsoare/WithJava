package playing.banking;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Bank {
    private String nameOfBank;
    private ArrayList<Customer> listOfCustomers;

    private static String validateNameOfBank(String validateNameOfBank) throws InterruptedException {
        String valueToReturn = "None";

        if (validateNameOfBank.isBlank()) {
            System.err.printf("%n%s%s%n", " ".repeat(5), "ERROR please use a valid name starting with an alpha character!");
            TimeUnit.SECONDS.sleep(2);
        } else {
            valueToReturn = validateNameOfBank.toUpperCase();
        }

        return valueToReturn;
    }

    public Bank(String nameOfBank, ArrayList<Customer> listOfCustomers) throws InterruptedException {
        this.nameOfBank = validateNameOfBank(nameOfBank);
        this.listOfCustomers = listOfCustomers;
    }

    public String getNameOfBank() {
        return nameOfBank;
    }

    public void setNameOfBank(String nameOfBank) throws InterruptedException {
        this.nameOfBank = validateNameOfBank(nameOfBank);
    }

    public ArrayList<Customer> getListOfCustomers() {
        return listOfCustomers;
    }

    public void setListOfCustomers(ArrayList<Customer> listOfCustomers) {
        this.listOfCustomers = listOfCustomers;
    }

    public void addNewCustomer(Customer newCustomer) {
        if (this.getCustomer(newCustomer.getName()) != null) {
            System.out.printf("%n%s%s%s%s%n", " ".repeat(5), "Customer ", newCustomer.getName(), " is already in the list of customers!");
        } else {
            listOfCustomers.add(newCustomer);
        }
    }

    public void addTransactionAmount(String nameOfCustomer, Double amount) throws InterruptedException {
        this.getCustomer(nameOfCustomer).addTransaction(amount);
    }

    public Double transactionsAmountCalculation(String nameOfCustomer) {
        Double totalTransactions;

        try {
            totalTransactions = this.getCustomer(nameOfCustomer).calcTransactionMount();
        } catch (NumberFormatException e) {
            totalTransactions = 0.0;
        }

        return totalTransactions;
    }

    public Customer getCustomer(String nameOfCustomer) {

        for (Customer cst : listOfCustomers) {
            if (cst.getName().equalsIgnoreCase(nameOfCustomer)) {
                return cst;
            }
        }

        System.out.printf("%n%sCustomer ( %s ) wasn't found!", " ".repeat(5), nameOfCustomer);
        return null;
    }
}
