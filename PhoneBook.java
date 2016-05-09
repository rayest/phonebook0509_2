package cn.PhoneBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xubt on 4/23/16.
 */
public class PhoneBook {

    private List<Person> persons = new ArrayList<Person>();

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost/db?useUnicode=true" +
                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=true";
        String user = "root";
        String password = "199011081108";
        return DriverManager.getConnection(url, user, password);
    }

    public void addPerson(Person person) throws Exception {
        Connection connection = getConnection();
        String sql;
        Statement statement = connection.createStatement();
        sql = "INSERT persons(name,phoneNumber) VALUES ('" + person.getName() + "','" + person.getPhoneNumber() + "')";
        statement.executeUpdate(sql);
    }

    public List<Person> loadPersons() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        String sql;
        Statement statement = connection.createStatement();
        sql = "SELECT * FROM persons";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Person> persons = new ArrayList<Person>();
        while (resultSet.next()) {
            Person person = new Person();
            person.setName(resultSet.getString(1));
            person.setPhoneNumber(resultSet.getString(2));
            persons.add(person);
        }
        return persons;
    }

    public Person findPersonByName(String name) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String sql;
        sql = "SELECT * FROM persons WHERE name ='" + name + "'";
        ResultSet resultSet = statement.executeQuery(sql);
        Person person = new Person();

        while (resultSet.next()) {
            person.setName(resultSet.getString(1));
            person.setPhoneNumber(resultSet.getString(2));
            persons.add(person);
        }
        return person;
    }

    public Person editPerson(String name, Person person) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String sql;
        sql = "UPDATE persons SET name='" + person.getName() + "',phonenumber='" + person.getPhoneNumber() + "'WHERE name='" + name + "'";
        statement.executeUpdate(sql);
        return person;
    }

    public void deletePerson(String name) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String sql;
        sql = "DELETE FROM persons WHERE name='" + name + "'";
        statement.executeUpdate(sql);
    }
}







