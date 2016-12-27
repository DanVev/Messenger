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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Swing();
            }
        });
    }
}
