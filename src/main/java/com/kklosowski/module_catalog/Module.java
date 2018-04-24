package com.kklosowski.module_catalog;

public class Module {

    private String subject;
    private int level;
    private String name;
    private boolean discontinued;

    public Module(String subject, int level, String name, boolean discontinued) {
        this.subject = subject;
        this.level = level;
        this.name = name;
        this.discontinued = discontinued;
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
    public String toString() {
        return "Module{" +
                "subject='" + subject + '\'' +
                ", level=" + level +
                ", name='" + name + '\'' +
                '}';
    }
}
