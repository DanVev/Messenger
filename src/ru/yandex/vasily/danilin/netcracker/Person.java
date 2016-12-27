package ru.yandex.vasily.danilin.netcracker;

import sun.plugin.javascript.navig.Array;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/***
 * Created by Vasily Danilin on 05.12.2016.
 */
public class Person {
    private String name;
    private String surname;
    private Calendar birthdate;
    private ArrayList<Conference> conferences = new ArrayList<>();

    public Person(String name, String surname, Calendar birthdate) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Calendar getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Calendar birthdate) {
        this.birthdate = birthdate;
    }

    public ArrayList<Conference> getConferences() {
        return conferences;
    }

    private boolean addConference(Conference conf) {
        conferences.add(conf);
        return true;
    }

    public Conference createConference(String name) {
        Conference c = new Conference(name, this);
        this.addConference(c);
        return c;
    }

    public void setConferences(ArrayList<Conference> conferences) {
        this.conferences = conferences;
    }

    public void sendMessage(String text, Conference conf) {
        Message mes = new Message(this, text);
        conf.addMessage(mes);

    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
