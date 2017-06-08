package com.shellcore.java.saxparser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cesar. 07/06/2017.
 */
public class SAXHandler extends DefaultHandler {

    List<Student> stdList;
    Student std = null;
    String content = null;

    @Override
    public void startDocument() throws SAXException {
        stdList = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "student":
                std = new Student();
                std.id = attributes.getValue("id");
                std.active = attributes.getValue("active");
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        content = String.copyValueOf(ch, start, length)
                .trim();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "student":
                stdList.add(std);
                break;
            case "firstName":
                std.firstName = content;
                break;
            case "lastName":
                std.lastName = content;
                break;
            case "location":
                std.location = content;
                break;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        for (Student s :
                stdList) {
            System.out.println(s);
        }
    }
}
