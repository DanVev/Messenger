package ru.yandex.vasily.danilin.netcracker;

import sun.plugin.javascript.navig.Array;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/***
 * Created by Vasily Danilin on 05.12.2016.
 */
public class Person {
    private int id;
    private String name;
    private String surname;
    private Calendar birthdate;
    private ArrayList<Conference> conferences = new ArrayList<>();
    private static int countId = 0;

    public int getId() {
        return id;
    }

    public Person(String name, String surname, Calendar birthdate) {
        this.id = countId;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        countId++;
    }

    public String getName() {
        return name;
    }

    public Person(int id, String name, String surname, Calendar birthdate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        countId++;
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

    public String getParsedBirthDate() {
        String result = "";
        result += birthdate.get(GregorianCalendar.YEAR);
        result += " ";
        result += birthdate.get(GregorianCalendar.MONTH);
        result += " ";
        result += birthdate.get(GregorianCalendar.DAY_OF_MONTH);
        return result;
    }

    @Override
    public String toString() {
        return name + " " + surname + " (id = " + this.id + ")";
    }
}
