package com.kklosowski.module_catalog;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Module")
public class Module {

    @Id
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @Column(name="id")
    private Integer id;
    private String subject;
    private int level;
    @Column(unique=true)
    private String name;
    private Boolean discontinued;

//    public Module (String subject, int level, String name, boolean discontinued) {
//        this.subject = subject;
//        this.level = level;
//        this.name = name;
//        this.discontinued = discontinued;
//    }

    public Module(String subject, int level, String name, boolean discontinued) {
        this.subject = subject;
        this.level = level;
        this.name = name;
        this.discontinued = discontinued;
    }

    public Module() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    @Override
    public boolean equals(Object obj){
        return (obj instanceof Module)
                && ((Module) obj).getName().equals(this.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", level=" + level +
                ", name='" + name + '\'' +
                ", discontinued=" + discontinued +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
