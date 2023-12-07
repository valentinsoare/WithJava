package WithJava.StartingToBankWithJava.src.main.java.playing.banking;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Customer {
    private String name;
    private ArrayList<Double> transactions;

    private static String validateName(String name) throws InterruptedException {
        String valueToReturn = "None";
        String errorMessage = String.format("%n%sERROR - please use a valid name and it needs to start with an alpha character!%n"," ".repeat(5));

        if (name.isBlank()) {
            System.out.print(errorMessage);
            TimeUnit.SECONDS.sleep(2);
        } else {
            valueToReturn = StringUtils.capitalize(name);
        }

        return valueToReturn;
    }

    private static ArrayList<Double> validateTransactions(ArrayList<Double> transactions) throws InterruptedException {
        String infoMessage = String.format("%n%sINFO - transaction list is empty, you can add positive or negative doubles!!%n"," ".repeat(5));

        if (transactions.isEmpty()) {
            System.out.print(infoMessage);
            TimeUnit.SECONDS.sleep(2);
        }

        return transactions;
    }

    public Customer(String name, ArrayList<Double> transactions) throws InterruptedException {
        this.name = validateName(name);
        this.transactions = validateTransactions(transactions);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InterruptedException {
        this.name = validateName(name);
    }

    public List<Double> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Double> transactions) throws InterruptedException {
        this.transactions = validateTransactions(transactions);
    }

    protected void addTransaction(Double transactionAmount) throws InterruptedException {
        if ((transactionAmount.isNaN()) || (transactionAmount == 0)) {
            System.out.printf("%n%s%s", " ".repeat(5), "ERROR please use an amount different than zero!");
            TimeUnit.SECONDS.sleep(2);
        } else {
            transactions.add(transactionAmount);
        }
    }

    protected Double calcTransactionMount() {
        Double totalTransactionsAmount = 0.0;

        for (Double transaction : transactions) {
            totalTransactionsAmount += transaction;
        }

        return totalTransactionsAmount;
    }

    @Override
    public String toString() {
        return "Customer [" +
                "name='" + name + '\'' +
                ", transactions=" + transactions +
                '}';
    }
}
