package ru.yandex.vasily.danilin.netcracker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/***
 * Created by Vasily Danilin on 05.12.2016.
 */
public class Person {
    private String name;
    private String surname;
    private int a;
    private Calendar birthdate;

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

    public void sendMessage(String text, Conference conf) {
        Message mes = new Message(this, text);
        conf.addMessage(mes);
    }
    @Override
    public String toString() {
        return name + " " + surname;
    }
}
