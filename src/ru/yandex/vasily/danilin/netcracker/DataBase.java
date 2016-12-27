package ru.yandex.vasily.danilin.netcracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.xml.parsers.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.xml.sax.*;
import org.w3c.dom.*;

/*
 * Created by User on 27.12.2016.
 */
public class DataBase {
    private static DataBase ourInstance = new DataBase();
    private ArrayList<Person> persons;
    private ArrayList<Conference> conferences;

    public static DataBase getInstance() {
        return ourInstance;
    }

    private DataBase() {
        persons = new ArrayList<>();
        conferences = new ArrayList<>();
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public ArrayList<Conference> getConferences() {
        return conferences;
    }

    public void addPerson(Person p) {
        persons.add(p);
    }

    public void addConference(Conference c) {
        conferences.add(c);
    }

    public void savePersonsToXML(String xml) {
        Document dom;
        Element e = null;

        // instance of a DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        try {
            // use factory to get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // create instance of DOM
            dom = db.newDocument();

            // create the root element
            Element rootEle = dom.createElement("persons");
            for (Person p : persons) {
                e = dom.createElement("person");
                e.setAttribute("id", "" + p.getId());
                e.setAttribute("name", p.getName());
                e.setAttribute("surname", p.getSurname());
                e.setAttribute("birthdate", p.getParsedBirthDate());
                rootEle.appendChild(e);
            }

            // create data elements and place them under root

            dom.appendChild(rootEle);

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                //tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "persons.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(dom),
                        new StreamResult(new FileOutputStream(xml)));

            } catch (TransformerException | IOException te) {
                System.out.println(te.getMessage());
            }
        } catch (ParserConfigurationException pce) {
            System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
        }
    }

    public void saveConferencesToXML(String xml) {
        Document dom;
        Element e = null;

        // instance of a DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        try {
            // use factory to get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // create instance of DOM
            dom = db.newDocument();

            // create the root element
            Element rootEle = dom.createElement("conferences");
            for (Conference c : conferences) {
                e = dom.createElement("conference");
                e.setAttribute("name", c.getName());
                Element pList = dom.createElement("persons");
                for (Person p : c.getPeople()) {
                    Element personNode = dom.createElement("person");
                    personNode.setAttribute("id", "" + p.getId());
                    pList.appendChild(personNode);
                }

                e.appendChild(pList);
                Element mList = dom.createElement("messages");
                for (Message m : c.getMessages()) {
                    Element messageNode = dom.createElement("message");
                    messageNode.setAttribute("sender", "" + m.getSender().getId());
                    messageNode.setAttribute("date", m.getParsedDate());
                    messageNode.appendChild(dom.createTextNode(m.getText()));
                    mList.appendChild(messageNode);
                }
                e.appendChild(mList);
                rootEle.appendChild(e);
            }

            // create data elements and place them under root

            dom.appendChild(rootEle);

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                //tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "conferences.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(dom),
                        new StreamResult(new FileOutputStream(xml)));

            } catch (TransformerException | IOException te) {
                System.out.println(te.getMessage());
            }
        } catch (ParserConfigurationException pce) {
            System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
        }
    }

    public void readPersonXML(String xml) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            File file = new File(xml);
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList persons = doc.getElementsByTagName("person");
            for (int temp = 0; temp < persons.getLength(); temp++) {
                Node nNode = persons.item(temp);
                Element e = (Element) nNode;
                String[] rawDate = e.getAttribute("birthdate").split(" ");
                GregorianCalendar birthdate = new GregorianCalendar(Integer.parseInt(rawDate[0]), Integer.parseInt(rawDate[1]), Integer.parseInt(rawDate[2]));
                this.persons.add(new Person(Integer.parseInt(e.getAttribute("id")), e.getAttribute("name"),
                        e.getAttribute("surname"), birthdate));
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void readConferenceXML(String xml) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            File file = new File(xml);
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList confs = doc.getElementsByTagName("conference");
            for (int temp = 0; temp < confs.getLength(); temp++) {
                ArrayList<Person> persons = new ArrayList<>();
                ArrayList<Message> messages = new ArrayList<>();
                Node nNode = confs.item(temp);
                Element e = (Element) nNode;
                String confName = e.getAttribute("name");
                NodeList nMessages = e.getElementsByTagName("message");
                NodeList nPersons = e.getElementsByTagName("person");
                for (int i = 0; i < nPersons.getLength(); i++) {
                    Element nPerson = (Element) nPersons.item(i);
                    int id = Integer.parseInt(nPerson.getAttribute("id"));
                    for (Person p : this.persons)
                        if (p.getId() == id) persons.add(p);
                }
                for (int i = 0; i < nMessages.getLength(); i++) {
                    Element nMessage = (Element) nMessages.item(i);
                    String mText = nMessage.getTextContent();
                    int senderId = Integer.parseInt(nMessage.getAttribute("sender"));
                    Person sender = null;
                    for (Person p : this.persons)
                        if (p.getId() == senderId) sender = p;
                    String[] rawDate = nMessage.getAttribute("date").split(" ");
                    Calendar date = new GregorianCalendar(Integer.parseInt(rawDate[0]), Integer.parseInt(rawDate[1]), Integer.parseInt(rawDate[2]), Integer.parseInt(rawDate[3]), Integer.parseInt(rawDate[4]));
                    messages.add(new Message(sender, mText, date));
                }
                this.conferences.add(new Conference(persons, confName, messages));
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
