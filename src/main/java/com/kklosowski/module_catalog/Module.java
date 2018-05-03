package com.kklosowski.module_catalog;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * The type Module.
 */
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

    /**
     * Instantiates a new Module.
     *
     * @param subject      the subject
     * @param level        the level
     * @param name         the name
     * @param discontinued the discontinued
     */
    public Module(String subject, int level, String name, boolean discontinued) {
        this.subject = subject;
        this.level = level;
        this.name = name;
        this.discontinued = discontinued;
    }

    /**
     * Instantiates a new Module.
     */
    public Module() {
    }

    /**
     * Gets subject.
     *
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets subject.
     *
     * @param subject the subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Is discontinued boolean.
     *
     * @return the boolean
     */
    public boolean isDiscontinued() {
        return discontinued;
    }

    /**
     * Sets discontinued.
     *
     * @param discontinued the discontinued
     */
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
