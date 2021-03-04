package ru.job4j.serilization;

import java.util.Arrays;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "cat")
@XmlAccessorType(XmlAccessType.FIELD)
public class Cat { //класс
    @XmlAttribute
    private String name;
    @XmlAttribute
    private boolean sterilized;
    @XmlAttribute
    private int age;

    @XmlElementWrapper
    @XmlElement(name = "child")
    private String[] children;
    private Contact contact;

    public Cat() {
    }

    public Cat(String name, boolean sterilized, int age, String[] children, Contact contact) {
        this.name = name;
        this.sterilized = sterilized;
        this.age = age;
        this.children = children;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", sterilized=" + sterilized +
                ", age=" + age +
                ", children=" + Arrays.toString(children) +
                ", contact=" + contact +
                '}';
    }

    public static void main(String[] args) {

        Cat cat = new Cat("myao", true, 23, new String[]{"myao2", "myao3"},
                new Contact(234, "89242640011"));
        final Gson gson = new GsonBuilder().create();
        String catJson = gson.toJson(cat); //серилизуем кота в json
        System.out.println(catJson);
        final Cat catFromJson = gson.fromJson(catJson, Cat.class); //десериализуем кота
        System.out.println(cat.toString() + System.lineSeparator() + catFromJson.toString());
    }
}
