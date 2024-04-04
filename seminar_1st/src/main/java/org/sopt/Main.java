package org.sopt;

import org.sopt.classes.Person;
import org.sopt.classes.PersonBuilder;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("박수빈", 23,"female");
        person.walk();
        Person.run();
        System.out.println(person.getName());
        Person personWithBuilder = new PersonBuilder().name("박수빈").age(23).sex("female").build();
        Person personWithFactoryMethod = Person.create("박수빈", 23,"female");
    }
}