package org.bankingapplicationwiththreading;

public class PrintNum extends Thread {
    private int num;
    private int times;

    public PrintNum(int num, int times) {
        this.num = num;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {

            System.out.printf("%s ", num);
        }
    }
}
