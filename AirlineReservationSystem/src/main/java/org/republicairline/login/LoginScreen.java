package org.republicairline.login;

import java.io.Console;
import java.util.Arrays;

public final class LoginScreen {
    private final HeaderForLoginScreen loginHeader;
    private final Integer emptySpacesForContentFromLeftEdge;

    public LoginScreen() {
        this.loginHeader = new HeaderForLoginScreen("republica air", "be on time", 20,
                2, 2);
        this.emptySpacesForContentFromLeftEdge = getLoginHeader().getEmptySpacesFromLeftEdgeScreen() / 2;
    }

    public HeaderForLoginScreen getLoginHeader() {
        return loginHeader;
    }

    public static void catchPasswordLoginSession() throws InterruptedException {
        Console consoleToCatchPasswd = System.console();

        if (consoleToCatchPasswd == null) {
            System.out.printf("%n%s", "\u001B[1;31mCannot load the console!\u001B[0m");
            System.exit(0);
        }

        System.out.printf("%n%s", " --> Please enter your password: ");
        char[] passwordEntered = consoleToCatchPasswd.readPassword();

        System.out.printf("%n%s%s", "Password entered was ", Arrays.toString(passwordEntered));
    }

    public void drawScreen() {
        System.out.printf("%s", loginHeader.generateHeader(1,
                0, '-', '|'));
    }
}
