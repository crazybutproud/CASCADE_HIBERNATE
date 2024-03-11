package ru.anna.RestApiConsumer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.anna.RestApiConsumer.entity.Item;
import ru.anna.RestApiConsumer.entity.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();

            Person person = new Person("Tim", 44);
            Item item1 = new Item("Cascading item1");  //вынесли процесс двойного связаывания в отдельный метод
            Item item2 = new Item("Cascading item2");
            Item item3 = new Item("Cascading item3");
            person.addItem(item1);
            person.addItem(item2);
            person.addItem(item3);
//            session.persist(person); // save. каскадирование только на этом методе. разница - save hibernate( нет  в других jpa).persist jpa only. @OneToMany(mappedBy = "person",cascade = CascadeType.PERSIST)
            session.save(person); // с помощью каскадирования у нас добавятся все товары сразу и один человек

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
