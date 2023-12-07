package WithJava.PhonesAndEmails.src.main.java.playing.phonesandemails;

import java.util.*;

public class App {
    public static void main( String[] args ) {
        ContactData data = new ContactData();

        List<Contact> emails = data.getData("emails");
        List<Contact> phones = data.getData("phones");

//        Set<Contact> phoneContacts = new HashSet<>(phones);
//        Set<Contact> emailContacts = new HashSet<>(emails);

//        System.out.println();
//        phoneContacts.forEach(System.out::println);
//        System.out.printf("%n%s%n%n", "-".repeat(60));
//        emailContacts.forEach(System.out::println);
//
//        int index = emails.indexOf(new Contact("Robin Hood"));
//        Contact robinhood = emails.get(index);
//        robinhood.addEmail("republica from bucharest");
//        robinhood.replaceEmailIfExists("RHood@republicafrombucharest.com", "RHood@republicafrombucharest.org");
////        System.out.printf("%n%s", robinhood);
//
//        //union of sets
//        Set<Contact> unionEmailsPhones = new HashSet<>();
//        unionEmailsPhones.addAll(emailContacts);
//        unionEmailsPhones.addAll(phoneContacts);
//
//        System.out.println("\n\n");
//        unionEmailsPhones.forEach(System.out::println);
//
//
//        //Intersection of sets
//        Set<Contact> intersectionEmailsPhones = new HashSet<>(emailContacts);
//        intersectionEmailsPhones.retainAll(phoneContacts);
//
//        System.out.println("\n\n");
//        intersectionEmailsPhones.forEach(System.out::println);
//
//
//        intersectionEmailsPhones = new HashSet<>(phoneContacts);
//        intersectionEmailsPhones.retainAll(emailContacts);
//
//        System.out.println("\n\n");
//        intersectionEmailsPhones.forEach(System.out::println);
//
//
//        //Asymmetric difference
//        Set<Contact> emailsMinusPhones = new HashSet<>(emailContacts);
//        emailsMinusPhones.removeAll(phoneContacts);
//
//        System.out.println("\n\n");
//        emailsMinusPhones.forEach(System.out::println);
//
//
//        Set<Contact> phonesMinusEmails = new HashSet<>(phoneContacts);
//        phonesMinusEmails.removeAll(emailContacts);
//
//        System.out.println("\n\n");
//        phonesMinusEmails.forEach(System.out::println);
//
//
//        //Symmetric Difference;
//
//        Set<Contact> symmetricDifferenceEmailsPhones = new HashSet<>(emailContacts);
//        Set<Contact> result = new HashSet<>();
//
//        symmetricDifferenceEmailsPhones.removeAll(phoneContacts);
//        result.addAll(symmetricDifferenceEmailsPhones);
//
//        Set<Contact> symmetricDifferencePhonesEmails = new HashSet<>(phoneContacts);
//        symmetricDifferencePhonesEmails.removeAll(emailContacts);
//        result.addAll(symmetricDifferencePhonesEmails);
//
//
//        System.out.println("\n\n");
//        result.forEach(System.out::println);

        //-------------------------------------------

//        NavigableSet<Contact> sorted = new TreeSet<>(phones);
        Comparator<Contact> mySort = Comparator.comparing(Contact::getName);

        NavigableSet<Contact> sorted = new TreeSet<>(mySort);
        sorted.addAll(phones);

//        sorted.forEach(System.out::println);

        Contact minimum = Collections.min(sorted, sorted.comparator());
        Contact maximum = Collections.max(sorted, sorted.comparator());

//        System.out.println(minimum);
//        System.out.println(maximum);

        Contact first = sorted.first();
        Contact last = sorted.last();

        NavigableSet<Contact> copySorted = new TreeSet<>(mySort);
        copySorted.addAll(sorted);

//        System.out.println(copySorted.pollFirst());
//        System.out.println(copySorted.pollLast());

        //---------------------------------------------------

        Contact daffy = new Contact("Daffy Duck");
        Contact daisy = new Contact("Daisy Duck");
        Contact snoopy = new Contact("Snoopy");
        Contact archie = new Contact("Archie");

//        for (Contact c : List.of(daffy, daisy, snoopy, archie, last)) {
//            System.out.printf("%nCeiling: %s -  %s", c.getName(), sorted.ceiling(c));
//            System.out.printf("%nHigher: %s - %s%n", c.getName(), sorted.higher(c));
//        }
//
//        for (Contact c : List.of(daffy, daisy, snoopy, archie, last)) {
//            System.out.printf("%nFloor: %s -  %s", c.getName(), sorted.floor(c));
//            System.out.printf("%nLoewer: %s - %s%n", c.getName(), sorted.lower(c));
//        }


//        System.out.println(sorted);

        NavigableSet<Contact> descendingSet = sorted.descendingSet();

        System.out.println(descendingSet);

        Contact marion = new Contact("Maid Marion");

        SortedSet<Contact> headSet = sorted.headSet(marion);
        System.out.println(headSet);

        SortedSet<Contact> tailSet = sorted.tailSet(marion);
        System.out.println(tailSet);

        Contact linus = new Contact("Linus Van Pelt");
        SortedSet<Contact> subset = sorted.subSet(linus, marion);

        System.out.println(subset);











    }
}
