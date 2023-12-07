package playing.phonesandemails;

import java.util.Set;
import java.util.HashSet;

public class Contact {
    private String name;
    private Set<String> emails = new HashSet<>();
    private Set<String> phones = new HashSet<>();

    public Contact(String name) {
        this(name, null, 0);
    }

    public Contact(String name, String email) {
        this(name, email, 0);
    }

    public Contact(String name, long phone) {
        this(name, null, phone);
    }

    private String convertPhoneNumber(long phoneNumber) {
        String numberAsString = String.valueOf(phoneNumber);
        return "(" + numberAsString.subSequence(0, 3) + ") " + numberAsString.subSequence(3, 6) + "-" + numberAsString.subSequence(6, 10);
    }

    public Contact(String name, String email, long phone) {
        this.name = name;

        if (email != null) {
            emails.add(email);
        }

        if (phone != 0) {
            phones.add(convertPhoneNumber(phone));
        }
    }

    public String getName() {
        return name;
    }

    public Contact mergeContactData(Contact contact) {
        Contact newObject = new Contact(this.name);

        newObject.emails.addAll(contact.emails);
        newObject.phones.addAll(contact.phones);

        return newObject;
    }

    public Set<String> getEmails() {
        return emails;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void addEmail(String companyName) {
        String[] names = name.split(" +");

        String email = String.format("%c%s@%s.com", name.charAt(0), names[names.length - 1],
                companyName.replaceAll(" ", "").toLowerCase());
        emails.add(email);
    }

    public void replaceEmailIfExists(String oldEmail, String newEmail) {
        if (emails.contains(oldEmail)) {
            emails.remove(oldEmail);
            emails.add(newEmail);
        }
    }

    @Override
    public String toString() {
        return String.format("Name: %s; Phone: %s; Email: %s", name, (phones.isEmpty()) ? "none" : String.join(", ",phones),
                (emails.isEmpty()) ? "none" : String.join(", ", emails));
    }

    @Override
    public boolean equals(Object o) {
        boolean valueToReturn;

        if (this == o) {
            valueToReturn = true;
        } else if (o == null || getClass() != o.getClass()) {
            valueToReturn = false;
        } else {
            Contact contact = (Contact) o;
            valueToReturn = getName().equalsIgnoreCase(contact.getName());
        }

        return valueToReturn;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + getName().hashCode();

        return result;
    }
}
