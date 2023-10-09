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

        people.add(new Person(++PEOPLE_COUNT, "Alex"));
        people.add(new Person(++PEOPLE_COUNT, "Bob"));
        people.add(new Person(++PEOPLE_COUNT, "Charley"));
        people.add(new Person(++PEOPLE_COUNT, "Delta"));

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
}
