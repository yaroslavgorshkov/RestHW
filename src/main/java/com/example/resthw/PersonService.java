package com.example.resthw;

import java.util.List;

public class PersonService {
    private static final PersonDao personDao = PersonDao.getINSTANCE();

    public static void save(Person person) {
        personDao.savePerson(person);
    }

    public static void delete(int personId) {
        personDao.deleteById(personId);
    }

    public static void deleteAll() {
        personDao.deleteAll();
    }

    public static Person get(int personId) {
        return personDao.getById(personId);
    }

    public static List<Person> getAll() {
        return personDao.getAll();
    }

}
