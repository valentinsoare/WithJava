package WithJava.StartingToBankWithJava.src.main.java.playing.banking;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Bank BCR = new Bank("BCR", new ArrayList<Customer>());

        BCR.addNewCustomer(new Customer("Valentin", new ArrayList<Double>(List.of(40.0, 20.0, 30.22, 44.35, -23.12))));
        BCR.addNewCustomer(new Customer("Gabriela", new ArrayList<Double>(List.of(40.10, 23.44, 150.23, -50.99))));


        System.out.println(BCR.getListOfCustomers());
        System.out.println(BCR.transactionsAmountCalculation("gabriela"));
        BCR.addTransactionAmount("gabriela", 100001.23);

        System.out.println(BCR.getCustomer("gabriela").getTransactions());
    }
}
