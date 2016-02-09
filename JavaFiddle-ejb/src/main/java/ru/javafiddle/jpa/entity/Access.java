package ru.javafiddle.jpa.entity;

/**
 * Created by Fedor on 18.11.2015.
 */

import javax.persistence.*;

/**
 * Specifies the access type of users group (read only, edit&read etc.)
 */
@Entity
@Table(name = "\"Access\"")
public class Access {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "\"accessId\"")
    private int accessId;
    @Column(name = "\"accessName\"")
    private String accessName;

    public Access(int accessId, String accessName) {
        this.accessId = accessId;
        this.accessName = accessName;
    }

    public Access() {
    }

    public int getAccessId() {
        return accessId;
    }

    public void setAccessId(int accessId) {
        this.accessId = accessId;
    }

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }

    @Override
    public String toString() {
        return "Access{" +
                "accessId=" + accessId +
                ", accessName='" + accessName + '\'' +
                '}';
    }
}
