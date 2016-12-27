package ru.yandex.vasily.danilin.netcracker;

import java.util.ArrayList;

/***
 * Created by Vasily Danilin on 06.12.2016.
 */
public class Conference {
    private ArrayList<Person> people = new ArrayList<>();
    private String name;
    private ArrayList<Message> messages = new ArrayList<>();

    public ArrayList<Person> getPeople() {
        return people;
    }

    public String getName() {
        return name;
    }

    public Conference(String name, Person creator) {

        this.name = name;
        this.people.add(creator);
    }

    public Conference(ArrayList<Person> people, String name, ArrayList<Message> messages) {
        this.people = people;
        this.name = name;
        this.messages = messages;
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
