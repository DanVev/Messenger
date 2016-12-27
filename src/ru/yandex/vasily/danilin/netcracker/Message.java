package ru.yandex.vasily.danilin.netcracker;

import java.util.Calendar;
import java.util.GregorianCalendar;

/***
 * Created by Vasily Danilin on 06.12.2016.
 */
public class Message {
    private Person sender;
    private String text;
    private Calendar date;

    public Message(Person sender, String text) {
        this.sender = sender;
        this.text = text;
        this.date = new GregorianCalendar();
    }


    public Person getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "'" + text + "' by " + sender + " at " + date.getTime();
    }

    public Calendar getDate() {
        return date;
    }

    public String getParsedDate() {
        String result = "";
        result += date.get(GregorianCalendar.YEAR);
        result += " ";
        result += date.get(GregorianCalendar.MONTH);
        result += " ";
        result += date.get(GregorianCalendar.DAY_OF_MONTH);
        result += date.get(GregorianCalendar.HOUR);
        result += "";
        result += date.get(GregorianCalendar.MINUTE);
        return result;
    }
}
