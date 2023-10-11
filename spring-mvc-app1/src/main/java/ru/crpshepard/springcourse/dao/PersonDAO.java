package ru.crpshepard.springcourse.dao;

import ru.crpshepard.springcourse.models.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/spring-mvc-app1";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "123";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return people;
    }

    public Person show(int id) {
        // System.out.println("Person show()");
        // for (int i = 0; i < people.size(); i++) {
        //     if (i == id - 1) {
        //         System.out.println(people.get(i).getId());
        //         System.out.println(people.get(i).getName());
        //         return people.get(i);
        //     }
        // }

         return null;
    }

    public void save(Person person) {
        // person.setId(++PEOPLE_COUNT);
        // people.add(person);

        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Person VALUES(" + 1 + ",'" + person.getName() + "'," + person.getAge() + ",'" + person.getEmail() + "')";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void update(int id, Person updatedPerson) {
        // Person personToBeUpdated = show(id);

        // personToBeUpdated.setName(updatedPerson.getName());
        // personToBeUpdated.setAge(updatedPerson.getAge());
        // personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
        // for (int i = 0; i < people.size(); i++) {
        //     if (i == id - 1) {
        //         people.remove(i);
        //     }
        // }
    }
}
