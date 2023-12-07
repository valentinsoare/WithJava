package org.bankingapplicationwiththreading;

public class PrintChar implements Runnable {
    private char letter;
    private int times;

    public PrintChar(char letter, int times) {
        this.letter = letter;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            System.out.printf("%s ", letter);
        }
    }
}
