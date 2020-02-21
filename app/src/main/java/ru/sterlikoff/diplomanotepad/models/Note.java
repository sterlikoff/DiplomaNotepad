package ru.sterlikoff.diplomanotepad.models;

import java.util.Date;

public class Note {

    public String title;
    public String text;

    private Date dateCreated;
    public Date dateDeadLine;

    public Note(String title, String text, Date dateDeadLine) {

        this.title = title;
        this.text = text;
        this.dateDeadLine = dateDeadLine;

    }

}