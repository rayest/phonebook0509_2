package cn.PhoneBook;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xubt on 4/23/16.
 */
public class Application {
    private static PhoneBook phoneBook = new PhoneBook();

    public static void main(String[] args) throws Exception {
        enterKey();
    }

    public static void enterKey() throws Exception {
        System.out.println("Press Enter key to continue:");
        new Scanner(System.in).nextLine();
        backToHome();
    }

    public static void backToHome() throws Exception {
        System.out.println("\nWelcome to phonebook!");
        System.out.println("Select operation:");
        System.out.println("1    Add contact");
        System.out.println("2   Find contact");
        System.out.println("3 Delete contact");
        System.out.println("4   Edit contact");
        System.out.println("5   View contact\n");
        Scanner input = new Scanner(System.in);
        int selectedOption = input.nextInt();
        switch (selectedOption) {
            case 1:
                input = new Scanner(System.in);
                System.out.println("Enter contact's name:");
                String addName = input.next();
                Person foundPerson_0 = phoneBook.findPersonByName(addName);
                if (foundPerson_0.getName() == null) {
                    Person newPerson = new Person();
                    newPerson.setName(addName);
                    System.out.println("Enter contact's phoneNumber:");
                    String addPhoneNumber = input.next();
                    Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
                    Matcher m = p.matcher(addPhoneNumber);
                    if (m.matches()) {
                        if (addPhoneNumber.equals(foundPerson_0.getPhoneNumber())) {
                            System.out.println("existed");
                        } else {
                            newPerson.setPhoneNumber(addPhoneNumber);
                            phoneBook.addPerson(newPerson);
                            System.out.println(newPerson.getName() + " successfully added!\n");
                        }
                    } else {
                        System.out.println("Entered a wrong number");
                        System.out.println("Try again");
                    }
                } else {
                    System.out.println("addName existed");
                    System.out.println("Try it again");

                }
                break;
            case 2:
                input = new Scanner(System.in);
                System.out.println("Enter contact's name");
                String contactName = input.next();
                Person foundPerson = phoneBook.findPersonByName(contactName);
                if (foundPerson.getName() == null) {
                    System.out.println("Contact can not be found\n");
                } else {
                    System.out.println(foundPerson.getName() + ":" + foundPerson.getPhoneNumber());
                }
                break;
            case 3:
                input = new Scanner(System.in);
                System.out.println("Enter contact's name");
                String deleteName = input.next();
                phoneBook.deletePerson(deleteName);
                System.out.println(deleteName + " successfully deleted!\n");
                break;
            case 4:
                input = new Scanner(System.in);
                System.out.println("Enter contact's name");
                String oldName = input.next();
                foundPerson = phoneBook.findPersonByName(oldName);
                if (foundPerson == null) {
                    System.out.println("Contact can not be found");
                } else {
                    System.out.println("Enter contact's new name");
                    String newName = input.next();
                    foundPerson.setName(newName);
                    System.out.println("Enter contact's new phone number:");
                    String addPhoneNumber = input.next();

                    Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
                    Matcher m = p.matcher(addPhoneNumber);
                    if (m.matches()) {
                        foundPerson.setPhoneNumber(addPhoneNumber);
                        phoneBook.editPerson(oldName, foundPerson);
                        System.out.println(foundPerson.getName() + " successfully edited!\n");
                    } else {
                        System.out.println("Entered a wrong number");
                    }
                }
                break;
            case 5:

                List<Person> persons = phoneBook.loadPersons();
                System.out.println("Contacts' Information:");
                for (Person person : persons) {
                    System.out.println("Name:" + person.getName());
                    System.out.println("PhoneNumber:" + person.getPhoneNumber());
                    System.out.println("----------------------------------");
                }
        }
        enterKey();
    }
}
