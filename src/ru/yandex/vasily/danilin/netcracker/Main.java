package ru.yandex.vasily.danilin.netcracker;

import javax.swing.*;
import java.util.GregorianCalendar;

/***
 * Created by Vasily Danilin on 07.12.2016.
 */
public class Main {
    public static void main(String[] args) {

        Person person1 = new Person("Vasily", "Danilin", new GregorianCalendar(1996, 6, 25));
        Person person2 = new Person("Olga", "Kiseleva", new GregorianCalendar(1996, 7, 3));
        Conference conf = person1.createConference("Private Chat");
        conf.addPerson(person2);
        person1.sendMessage("Hello, Olya!", conf);
        person2.sendMessage("Hi, Vasya!", conf);
        DataBase data = DataBase.getInstance();
        data.addPerson(person1);
        data.addPerson(person2);
        data.savePersonsToXML("MyTestPersons.xml");
        data.addConference(conf);
        data.saveConferencesToXML("MyTestConferences.xml");
        for (Message m : conf.getMessages()) {
            System.out.println(m);
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Swing();
            }
        });
    }
}
