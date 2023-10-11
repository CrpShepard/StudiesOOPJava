package ru.crpshepard.springcourse.dao;

import ru.crpshepard.springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Alex", 18, "alex@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Bob", 24, "bob@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Charley", 27, "charley@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Delta", 33, "delta@mail.ru"));

    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        System.out.println("Person show()");
        for (int i = 0; i < people.size(); i++) {
            if (i == id - 1) {
                System.out.println(people.get(i).getId());
                System.out.println(people.get(i).getName());
                return people.get(i);
            }
        }

        return null;
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = show(id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
        for (int i = 0; i < people.size(); i++) {
            if (i == id - 1) {
                people.remove(i);
            }
        }
    }
}
