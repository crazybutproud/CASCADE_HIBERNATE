package ru.anna.RestApiConsumer.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Item")
public class Item { //дочерняя сущность,сторона many

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "person_id",insertable=false, updatable=false)
    private int personId;

    @Column(name = "item_name")
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id") //тут была ошибка
    private Person person; //у товара есть владелец

    public Item() {

    }

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", personId=" + personId +
                ", itemName='" + itemName + '\'' +
                ", person=" + person +
                '}';
    }
}
