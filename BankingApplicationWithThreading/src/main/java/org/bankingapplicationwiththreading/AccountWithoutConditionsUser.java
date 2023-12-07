package org.bankingapplicationwiththreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountWithoutConditionsUser {
	private static Account account = new Account();
	private static Lock lock  = new ReentrantLock(true);

	public static void main(String[] args) {
		System.out.println("Thread 1\t\tThread 2\t\tBalance");
		ExecutorService executor = Executors.newCachedThreadPool();

		for (int i = 0; i < 100; i++) {
			executor.execute(new DepositTask());
		}

		executor.shutdown();

		try {
			executor.awaitTermination(30, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
		}


		while(!executor.isShutdown()) {
		}

		System.out.printf("%nWhat is balance ? %d", account.getBalance());
	}
	public static class DepositTask implements Runnable {
		public synchronized void run() {
			account.deposit(1);
		}
	}
	
	public static class WithdrawTask implements Runnable {
		public void run() {
			while(true) {
				account.withdraw((int)(Math.random() * 10) + 1);
			}
		}
	}
	
	private static class Account {
		private int balance = 0;

		public int getBalance() {
			return balance;
		}
		
		public void withdraw(int amount) {
			
		}
		
		public void deposit(int amount) {
			lock.lock();
			try {
				this.balance += amount;
			} finally {
				lock.unlock();
			}
		}
	}
}