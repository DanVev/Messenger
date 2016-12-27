package ru.yandex.vasily.danilin.netcracker;

import javax.swing.*;
import java.util.GregorianCalendar;

/***
 * Created by Vasily Danilin on 07.12.2016.
 */
public class Main {
    public static void main(String[] args) {

        DataBase data = DataBase.getInstance();
        data.load("MyTest");
//        for (Person p : data.getPersons())
//            System.out.println(p);
//        data.readConferenceXML("MyTestConferences.xml");
//
//        Conference conf = data.getConferences().get(0);
//        for (Message m : conf.getMessages()) {
//            System.out.println(m);
//        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Swing();
            }
        });
    }
}
