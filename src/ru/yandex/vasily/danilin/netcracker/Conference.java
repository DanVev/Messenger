package ru.yandex.vasily.danilin.netcracker;

import java.util.ArrayList;

/***
 * Created by Vasily Danilin on 06.12.2016.
 */
public class Conference {
    private ArrayList<Person> people = new ArrayList<Person>();
    private String name;
    private ArrayList<Message> messages = new ArrayList<Message>();

    public ArrayList<Person> getPeople() {
        return people;
    }

    public String getName() {
        return name;
    }

    public Conference(String name) {

        this.name = name;
    }

    public void addMessage(Message m) {
        messages.add(m);
    }

    public void addPerson(Person p) {
        people.add(p);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
}
