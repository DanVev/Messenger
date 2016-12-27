package ru.yandex.vasily.danilin.netcracker;

/*
 * Created by User on 27.12.2016.
 */

import javax.swing.*;
import java.awt.*;

public class Swing extends JFrame {
    public Swing() {
        super("Messenger");
        setSize(630, 550);
        setLocation(150, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel grid = new JPanel();
        GridLayout gl = new GridLayout(1, 2, 20, 5);
        grid.setLayout(gl);
        // TODO: load users
        JComboBox<Person> userChoser = new JComboBox<>();
        userChoser.setPreferredSize(new Dimension(80, 25));
        // TODO: load confs
        JComboBox<Conference> confChoser = new JComboBox<>();
        confChoser.setPreferredSize(new Dimension(80, 25));

        grid.add(new JLabel("Choose the User"));
        grid.add(userChoser);
        grid.add(new JLabel("Choose the Conference"));
        grid.add(confChoser);
        add(grid);

        JTextField textField = new JTextField();
        textField.setEditable(false);

        textField.setPreferredSize(new Dimension(550, 300));
        add(textField);

        JTextField textMessage = new JTextField();
        textMessage.setPreferredSize(new Dimension(450, 150));
        add(textMessage);

        JButton sendButton = new JButton("Send");
        sendButton.setPreferredSize(new Dimension(100, 50));
        add(sendButton);
        setVisible(true);

    }
}