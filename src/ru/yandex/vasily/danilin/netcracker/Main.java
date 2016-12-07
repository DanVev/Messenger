package ru.yandex.vasily.danilin.netcracker;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/***
 * Created by Vasily Danilin on 07.12.2016.
 */
public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("Vasily", "Danilin", new GregorianCalendar(1996, 6, 25));
        Person person2 = new Person("Olga", "Kiseleva", new GregorianCalendar(1996, 7, 3));
        Conference conf = new Conference("Private chat");
        conf.addPerson(person1);
        conf.addPerson(person2);
        person1.sendMessage("Hello, Olya!", conf);
        person2.sendMessage("Hi, Vasya!", conf);
        for (Message m : conf.getMessages()) {
            System.out.println(m.getText());
        }
    }
}
