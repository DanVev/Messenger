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
    private Person reciever;

    public Message(Person sender, String text) {
        this.sender = sender;
        this.text = text;
        this.date = new GregorianCalendar();
    }

    public Message(Person sender, String text, Person reciever) {
        this.sender = sender;
        this.text = text;
        this.reciever = reciever;
        this.date = new GregorianCalendar();
    }

    public Person getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }

    public Person getReciever() {
        return reciever;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setReciever(Person receiver) {
        this.reciever = receiver;
    }

    @Override
    public String toString() {
        return "'" + text + "' by " + sender + " at " + date.getTime();
    }
}
