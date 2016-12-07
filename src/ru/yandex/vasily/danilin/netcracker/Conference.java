package ru.yandex.vasily.danilin.netcracker;

import java.awt.*;
import java.util.ArrayList;

/***
 * Created by Vasily Danilin on 06.12.2016.
 */
public class Conference {
    ArrayList<Person> people;
    String name;

    public ArrayList<Person> getPeople() {
        return people;
    }

    public String getName() {
        return name;
    }

    public Conference(ArrayList<Person> people, String name) {

        this.people = people;
        this.name = name;
    }
}
