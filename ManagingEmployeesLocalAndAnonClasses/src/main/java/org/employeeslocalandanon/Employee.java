package org.employeeslocalandanon;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.security.SecureRandom;

public class Employee {
    private static int customIndex = 0;
    private enum names {James, Robert, John, David, Mary, Patricia, Jennifer, Linda, Elizabeth,
        Barbara, Susan, Jessica, Sarah, Lisa}
    private static SecureRandom rnd = new SecureRandom();
    private List<String> firstAndLast;

    private String firstName;
    private String lastName;
    private int yearHired;

    public Employee() {
        firstAndLast = parseNameAndRetrieve();

        this.firstName = firstAndLast.get(0);
        this.lastName = firstAndLast.get(1);
        this.yearHired = rnd.nextInt(12) + 2012;
    }

    public static List<String> parseNameAndRetrieve() {
        String toReturn = String.valueOf(names.values()[customIndex++ % names.values().length]);
        return new ArrayList<>(Arrays.asList(toReturn, String.valueOf(toReturn.charAt(rnd.nextInt(toReturn.length()))).toUpperCase()));
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getYearHired() {
        return yearHired;
    }

    @Override
    public String toString() {
        return String.format("%s %-3s%-5d", firstName, lastName + ".", yearHired);
    }
}

