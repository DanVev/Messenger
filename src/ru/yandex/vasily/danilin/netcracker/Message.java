package ru.yandex.vasily.danilin.netcracker;

/***
 * Created by Vasily Danilin on 06.12.2016.
 */
public class Message {
    private Person sender;
    private String text;
    private Person reciever;

    public Message(Person sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    public Message(Person sender, String text, Person reciever) {
        this.sender = sender;
        this.text = text;
        this.reciever = reciever;
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

    public void setReciever(Person reciever) {
        this.reciever = reciever;
    }
}
