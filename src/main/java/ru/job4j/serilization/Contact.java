package ru.job4j.serilization;


import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.*;
import java.nio.file.Files;
import java.util.Objects;

@XmlElement(value = "contact")
public class Contact {
    @XmlAttribute
    private int zipCode;
    @XmlAttribute
    private String phone;

    public Contact() {
    }

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "zipCode=" + zipCode +
                ", phone='" + phone + '\'' +
                '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");
//
//        /* Запись объекта в файл */
//        File tempFile = Files.createTempFile(null, null).toFile();
//        try (FileOutputStream fos = new FileOutputStream(tempFile);
//             ObjectOutputStream oos =
//                     new ObjectOutputStream(fos)) {
//            oos.writeObject(contact);
//        }
//
//        /* Чтение объекта из файла */
//        try (FileInputStream fis = new FileInputStream(tempFile);
//             ObjectInputStream ois =
//                     new ObjectInputStream(fis)) {
//            final Contact contactFromFile = (Contact) ois.readObject();
//            System.out.println(contactFromFile);
//            System.out.println(contact.equals(contactFromFile)); //test
//        }
    }
}

