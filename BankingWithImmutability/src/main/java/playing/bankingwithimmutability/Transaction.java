package WithJava.BankingWithImmutability.src.main.java.playing.bankingwithimmutability;

public final class Transaction {
    private final int routingNumber;
    private final String customerId;
    private final long transactionId;
    private final double transactionAmount;

    public Transaction(int routingNumber, String customerId,
                       long transactionId, double transactionAmount) {
        this.routingNumber = routingNumber;
        this.customerId = customerId;
        this.transactionId = transactionId;
        this.transactionAmount = transactionAmount;
    }

    public Transaction(Transaction t) {
        this.routingNumber = t.routingNumber;
        this.customerId = t.customerId;
        this.transactionId = t.transactionId;
        this.transactionAmount = t.transactionAmount;
    }

    public int getRoutingNumber() {
        return routingNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    @Override
    public String toString() {
        return String.format("%d, %s, %d, %.2f",
                routingNumber, customerId, transactionId, transactionAmount);
    }
}
