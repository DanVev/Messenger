package ru.yandex.vasily.danilin.netcracker;

/*
 * Created by User on 27.12.2016.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Swing extends JFrame implements ActionListener {
    JTextField textField;
    JTextField textMessage;
    JButton sendButton;
    JButton createUser;
    JButton createChat;
    JComboBox<Person> userChoser;
    JComboBox<Conference> confChoser;
    public Swing() {
        super("Messenger");
        setSize(630, 550);
        setLocation(150, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel grid = new JPanel();
        GridLayout gl = new GridLayout(2, 3, 20, 5);
        grid.setLayout(gl);
        // TODO: load users
        userChoser = new JComboBox<>();
        userChoser.setPreferredSize(new Dimension(80, 25));
        // TODO: load confs
        confChoser = new JComboBox<>();
        confChoser.setPreferredSize(new Dimension(80, 25));

        createUser = new JButton("Create a User");
        createUser.setPreferredSize(new Dimension(90, 25));
        createChat = new JButton("Create a Chat");
        createChat.setPreferredSize(new Dimension(90, 25));
        grid.add(new JLabel("Choose the User"));
        grid.add(userChoser);
        grid.add(createUser);
        grid.add(new JLabel("Choose the Conference"));
        grid.add(confChoser);
        grid.add(createChat);
        add(grid);


        JTextField textField = new JTextField();
        textField.setEditable(false);
        textField.setPreferredSize(new Dimension(550, 275));
        add(textField);

        JTextField textMessage = new JTextField();
        textMessage.setPreferredSize(new Dimension(450, 150));
        add(textMessage);

        JButton sendButton = new JButton("Send");
        sendButton.setPreferredSize(new Dimension(100, 50));
        sendButton.addActionListener(this);
        sendButton.setActionCommand("Send a Message");
        add(sendButton);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("send a Mesage".equals(e.getActionCommand())) {

        }
    }
}