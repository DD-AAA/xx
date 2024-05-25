import java.util.ArrayList;
import java.util.List;

/**
 * Represents a simple address book application.
 * Allows adding, removing, and listing contacts.
 */
public class AddressBook {

    private final List<Contact> contacts;

    public AddressBook() {
        this.contacts = new ArrayList<>();
    }

    /**
     * Adds a new contact to the address book.
     *
     * @param contact the contact to add
     * @throws IllegalArgumentException if contact is null
     */
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null.");
        }
        contacts.add(contact);
    }

    /**
     * Removes a contact from the address book.
     *
     * @param contact the contact to remove
     * @throws IllegalArgumentException if contact is null
     */
    public void removeContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null.");
        }
        contacts.remove(contact);
    }

    /**
     * Lists all contacts in the address book.
     *
     * @return an unmodifiable list of contacts
     */
    public List<Contact> listContacts() {
        return List.copyOf(contacts);
    }

    /**
     * Represents a contact in the address book.
     */
    public static class Contact {
        private final String name;
        private final String phoneNumber;
        private final String email;

        public Contact(String name, String phoneNumber, String email) {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Name cannot be null or blank.");
            }
            if (phoneNumber == null || phoneNumber.isBlank()) {
                throw new IllegalArgumentException("Phone number cannot be null or blank.");
            }
            if (email == null || email.isBlank()) {
                throw new IllegalArgumentException("Email cannot be null or blank.");
            }
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return "Contact{" +
                    "name='" + name + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        
        Contact john = new Contact("John Doe", "123-456-7890", "john@example.com");
        Contact jane = new Contact("Jane Smith", "098-765-4321", "jane@example.com");

        addressBook.addContact(john);
        addressBook.addContact(jane);

        System.out.println("Contacts:");
        addressBook.listContacts().forEach(System.out::println);
    }
}
