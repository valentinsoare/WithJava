package playing.gameOfCrapsOOP;

import java.util.concurrent.TimeUnit;

public class AuxChecks {
    public static String checkForQuitAndBack(String valueFromUser, int emptySpaces, boolean ifMainMenu) throws InterruptedException {
       String valueToReturn = valueFromUser;
       String errorMessage = (ifMainMenu) ? String.format("%n%s%s%n", " ".repeat(emptySpaces),"ERROR please choose an option from those mentioned above!")
               : String.format("%n%s%s%n", " ".repeat(emptySpaces), "ERROR please provide requested input or back/quit!");

       if (valueFromUser.isBlank()) {
           System.out.println(errorMessage);
           TimeUnit.SECONDS.sleep(1);
           valueToReturn = "none";
       } else {
           String inputFromUser = valueFromUser.toLowerCase().trim();

           if ("quit".equals(inputFromUser)) {
               String quitingMessage = String.format("%n%s%s", " ".repeat(emptySpaces), "Quiting");

               Loading.starting(5,'.', quitingMessage, 100, true, "DONE");
               Thread.sleep(100);

               if (ifMainMenu) {
                   return "quit";
               }

               System.exit(0);
           } else if ("back".equals(inputFromUser)) {
               valueToReturn = "back";
               String goingBackMessage = String.format("%n%s%s", " ".repeat(emptySpaces), "Going back");

               Loading.starting( 5,'.', goingBackMessage, 200, true, "DONE");
               Thread.sleep(500);
           }
       }

       return valueToReturn;
    }
}
