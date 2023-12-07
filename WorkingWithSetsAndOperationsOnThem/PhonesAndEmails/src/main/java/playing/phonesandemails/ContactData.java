package playing.phonesandemails;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class ContactData {
    private static String[] phones;
    private static String[] emails;

    static {
        String phoneData = """
                Charlie Brown, 3334445555
                Maid Marion, 1234567890
                Mickey Mouse, 9998887777
                Mickey Mouse, 1247489758
                Minnie Mouse, 4567805666
                Robin Hood, 5647893000
                Robin Hood, 7899928222
                Lucy Van Pelt, 5642086852
                Mickey Mouse, 9998887777
                """;

        String emailData = """
                Mickey Mouse, mckmouse@gmail.com
                Mickey Mouse, micky1@aws.com
                Minnie Mouse, minnie@verizon.net
                Robin Hood, rhood@gmail.com
                Linus Van Pelt, lvpelt2015@gmail.com
                Daffy Duck, daffy@google.com
                """;

        phones = phoneData.split("\n");
        emails = emailData.split("\n");
    }

    public static String[] getPhones() {
        return phones;
    }

    public static String[] getEmails() {
        return emails;
    }

    public static List<Contact> getData(String type) {
        List<Contact> toReturn = new ArrayList<>();
        String[] toParseAndUse = Arrays.copyOf(phones, phones.length);

        if ("emails".equalsIgnoreCase(type)) {
            toParseAndUse = Arrays.copyOf(emails, emails.length);
        }

        for (String e : toParseAndUse) {
            String[] temp = e.split(",");

            if ("emails".equalsIgnoreCase(type)) {
                toReturn.add(new Contact(temp[0].trim(), temp[1].trim()));
            } else {
                toReturn.add(new Contact(temp[0], Long.parseLong(temp[1].trim())));
            }
        }

        return toReturn;
    }
}
