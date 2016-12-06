package ru.yandex.vasily.danilin.netcracker;

import java.util.Date;

/***
 * Created by Vasily Danilin on 05.12.2016.
 */
public class Person {
    private String name;
    private String surname;
    private int a;
    private Date birthdate;

    public Person(String name, String surname, Date birthdate) {
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void sendMessage(String text, Conference conf) {

    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
