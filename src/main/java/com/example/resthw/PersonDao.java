package com.example.resthw;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class PersonDao {
    @Getter
    private static final PersonDao INSTANCE = new PersonDao();
    private final List<Person> personList = new ArrayList<>();


    public void savePerson(Person person) {
        personList.add(person);
    }

    public void deleteById(int id) {
        Person foundPerson = personList.stream().filter(person -> person.getId() == id).findAny().orElseThrow();
        personList.remove(foundPerson);
    }

    public void deleteAll() {
        personList.clear();
    }

    public Person getById(int id) {
        return personList.stream().filter(person -> person.getId() == id).findAny().orElseThrow();
    }

    public List<Person> getAll() {
        return new ArrayList<>(personList);
    }

    private PersonDao() {
        Person person1 = new Person();
        person1.setId(1);
        person1.setName("Yarik");
        person1.setHeight(185);

        Person person2 = new Person();
        person2.setId(2);
        person2.setName("Ivan");
        person2.setHeight(186);
        personList.add(person1);
        personList.add(person2);
    }
}
