package ru.yandex.vasily.danilin.netcracker;

/*
 * Created by User on 27.12.2016.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

public class Swing extends JFrame implements ActionListener {
    JTextField textField;
    JTextField textMessage;
    JButton sendButton;
    JButton createUser;
    JButton createChat;
    JComboBox<Person> userChoser;
    JComboBox<Conference> confChoser;
    Vector<Person> allPersons = new Vector<>(DataBase.getInstance().getPersons());
    Vector<Conference> userConferences;

    public Swing() {
        super("Messenger");
        setSize(630, 550);
        setLocation(150, 100);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame) e.getSource();

                int result = JOptionPane.showConfirmDialog(
                        frame,
                        "Are you sure you want to exit the application?",
                        "Exit Application"
                        ,
                        JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    DataBase.getInstance().save("MyTest");
                }
            }
        });

        setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel grid = new JPanel();
        GridLayout gl = new GridLayout(2, 3, 20, 5);
        grid.setLayout(gl);
        // TODO: load users
        userChoser = new JComboBox<>(allPersons);
        userChoser.setPreferredSize(new Dimension(80, 25));
        userChoser.setActionCommand("Changed user");
        userChoser.addActionListener(this);
        // TODO: load confs
        Person sel = (Person) userChoser.getSelectedItem();
        if (!sel.equals(null)) {
            userConferences = new Vector<>(sel.getConferences());
            confChoser = new JComboBox<>(userConferences);
            confChoser.setActionCommand("Changed conference");
            confChoser.addActionListener(this);
            confChoser.setEnabled(true);
        } else {
            confChoser = new JComboBox<>();
            confChoser.setEnabled(false);
        }

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
        textField.setPreferredSize(new Dimension(550, 375));
        add(textField);

        JTextField textMessage = new JTextField();
        textMessage.setPreferredSize(new Dimension(450, 50));
        add(textMessage);

        JButton sendButton = new JButton("Send");
        sendButton.setPreferredSize(new Dimension(100, 50));
        sendButton.setActionCommand("Send a Message");
        sendButton.addActionListener(this);
        add(sendButton);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("send a Mesage".equals(e.getActionCommand())) {

        }
        if ("Changed user".equals(e.getActionCommand())) {

            userConferences = new Vector<Conference>(((Person) userChoser.getSelectedItem()).getConferences());
            update(this.getGraphics());
            ((JComboBox) e.getSource()).updateUI();
            System.out.print("1");

        }
        if ("Changed conference".equals(e.getActionCommand())) {

        }
    }
}