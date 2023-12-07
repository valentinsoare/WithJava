package playing.phonesandemails;

import java.util.*;

public class MapMain {
    public static void main(String[] args) {
        List<Contact> phones = ContactData.getData("phones");
        List<Contact> emails = ContactData.getData("emails");

        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);

        System.out.printf("%n%s%n", "-".repeat(60));
        fullList.forEach(System.out::println);
        System.out.printf("%s%n", "-".repeat(60));

        Map<String, Contact> contacts = new HashMap<>();

        for (Contact c : fullList) {
            contacts.put(c.getName(), c);
        }

//        for (Map.Entry<String, Contact> toUse : contacts.entrySet()) {
//            System.out.printf("%nkey: %s, value: %s", toUse.getKey(), toUse.getValue());
//        }

        System.out.printf("%n%s%n", "-".repeat(60));

//        System.out.println(contacts.get("Maid Marion"));
//        System.out.println(contacts.getOrDefault("Nebunie", contacts.get("Daffy Duck")));

        contacts.clear();

        for (Contact c : fullList) {
            Contact duplicate = contacts.put(c.getName(), c);

            if (duplicate != null) {
                contacts.put(c.getName(), c.mergeContactData(duplicate));
            }
        }

//        for (Map.Entry<String, Contact> toBeUseForPrinting : contacts.entrySet()) {
//            System.out.printf("%n Key: %s Value: %s", toBeUseForPrinting.getKey(), toBeUseForPrinting.getValue());
//        }

//        contacts.clear();
//        System.out.printf("%n%s%n", "-".repeat(60));
//
//        for (Contact c : fullList) {
//            contacts.putIfAbsent(c.getName(), c);
//        }
//
//        for (Map.Entry<String, Contact> toBeUseForPrinting : contacts.entrySet()) {
//            System.out.printf("%n Key: %s Value: %s", toBeUseForPrinting.getKey(), toBeUseForPrinting.getValue());
//        }

//        System.out.println(contacts.keySet()); //keys
//        System.out.println(contacts.values());//values
//        System.out.println(contacts.entrySet());//entire hashmap

        Set<String> contactKeys = contacts.keySet();
        Set<String> sortedKeys = new TreeSet<>(contacts.keySet());

//        System.out.println(sortedKeys);

        if (contacts.containsKey("Robin Hood")) {
//            System.out.printf("%s", "Given key is in the list of keys!");
        }

        contactKeys.remove("Robin Hood");
        contacts.forEach((k, v) -> System.out.println(v));

//        contactKeys.removeAll(Arrays.asList("Lucy Van Pelt", "Mickey Mouse"));

//        System.out.println(contactKeys);

        System.out.printf("%n%s%n", "-".repeat(60));

        var values = contacts.values();

        values.forEach(System.out::println);
        values.retainAll(ContactData.getData("emails"));
        System.out.printf("%n%s%n", "-".repeat(60));
        contacts.forEach((k, v) -> System.out.println(v));

//        System.out.println(emails);

        System.out.printf("%n%s%n", "-".repeat(60));



    }
}
