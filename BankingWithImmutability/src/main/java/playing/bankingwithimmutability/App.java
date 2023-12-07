package playing.bankingwithimmutability;

public class App {
    public static void main(String[] args) {
        Bank ing = new Bank(187111952);

        ing.addCustomer("Valentin, Soare", 3_500, 4_000);
        ing.addCustomer("Andreea, Popescu", 2_000, 40_000);
        ing.addCustomer("Gabriela, Manea", 200, 15_000);

        ing.doTransaction(ing.getCustomerByName("valentin, soare").getCustomerID(), AccountType.SAVINGS, 100_000);
        ing.doTransaction(ing.getCustomerByName("andreea, popescu").getCustomerID(), AccountType.CHECKING, 50_000);
        ing.doTransaction(ing.getCustomerByName("gabriela, manea").getCustomerID(), AccountType.SAVINGS, -4500);

        BankCustomer valentin = ing.getCustomerByName("valentin, soare");

        System.out.printf("%n%s", ing.getCustomers().values());
    }
}
