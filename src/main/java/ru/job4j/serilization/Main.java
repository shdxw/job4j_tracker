package ru.job4j.serilization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        Cat cat = new Cat("myao", true, 23, new String[]{"myao2", "myao3"},
                new Contact(234, "89242640011"));
        // Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(Cat.class);
        // Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();
        // Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            // Сериализуем
            marshaller.marshal(cat, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        // Для десериализации нам нужно создать десериализатор
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            // десериализуем
           Cat result = (Cat) unmarshaller.unmarshal(reader);
            System.out.println(result.toString());
        }

    }
}