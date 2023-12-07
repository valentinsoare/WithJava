package WithJava.BankingApplicationWithThreading.src.main.java.org.bankingapplicationwiththreading;

import org.bankingapplicationwiththreading.PrintChar;
import org.bankingapplicationwiththreading.PrintNum;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main( String[] args ) {
        Runnable printFirst = new PrintChar('a', 500);
        Runnable printSecond = new PrintNum(5, 200);

//        Thread t1 = new Thread(printFirst);
//        Thread t2 = new Thread(printSecond);
//
//        t1.start();
//        t2.start();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                20,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5)
        );

        threadPoolExecutor.prestartAllCoreThreads();

        threadPoolExecutor.submit(printFirst);
        threadPoolExecutor.submit(printSecond);

        threadPoolExecutor.shutdown();
        try {
            threadPoolExecutor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
        }
    }
}
