package org.bankingapplicationwiththreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountWithConditionsUser {
    private static Account account = new Account();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new DepositTask());
        executor.execute(new WithdrawTask());

        executor.shutdown();

        try {
            executor.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
        }
    }

    public static class DepositTask implements Runnable{
        public void run() {
            try {
                while(true) {
                    account.deposit((int) (Math.random() * 10 + 1));
                    Thread.sleep(1000);
                }
            }
            catch(InterruptedException ex) {
            }
        }
    }

    public static class WithdrawTask implements Runnable{
        public void run() {
            while(true) {
                account.withdraw((int)(Math.random() * 10) + 1);
            }
        }
    }

    private static class Account{
        private static Lock lock = new ReentrantLock(true);
        private static Condition newDeposit = lock.newCondition();

        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        public void withdraw(int amount) {
            lock.lock();
            try {
                while (balance < amount) {
                    System.out.printf("%nNot enough money, waiting for deposit!");
                    newDeposit.await();
                }

                this.balance -= amount;
                System.out.printf("%nWithdraw of %s was success! Balance: %s", amount, balance);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

        public void deposit(int amount) {
            lock.lock();
            try {
                this.balance += amount;
                System.out.printf("%nDeposit was made with %s. Balance: %s", amount, balance);

                newDeposit.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }
}
