package ru.yandex.vasily.danilin.netcracker;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.*;
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
                tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "persons.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(dom),
                        new StreamResult(new FileOutputStream(xml)));

            } catch (TransformerException te) {
                System.out.println(te.getMessage());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
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
                tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "persons.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(dom),
                        new StreamResult(new FileOutputStream(xml)));

            } catch (TransformerException te) {
                System.out.println(te.getMessage());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        } catch (ParserConfigurationException pce) {
            System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
        }
    }

}
