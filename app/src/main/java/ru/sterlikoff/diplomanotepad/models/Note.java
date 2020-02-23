package ru.sterlikoff.diplomanotepad.models;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Date;

public class Note implements Comparable<Note> {

    private int id;

    public String title;
    public String text;

    private Date dateCreated;
    public Date dateDeadLine;
    private Note o;

    public Note() {

    }

    public Note(int id) {
        this.id = id;
    }

    public Note(String title, String text, Date dateDeadLine) {

        this.title = title;
        this.text = text;
        this.dateDeadLine = dateDeadLine;

    }

    public boolean validate() {
        return !title.isEmpty() && !text.isEmpty();
    }

    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
    }

    public JSONObject getJson() {

        JSONObject json = new JSONObject();

        try {

            json.put("title", this.title);
            json.put("text", this.text);
            json.put("dateDeadLine", this.dateDeadLine);

        } catch (JSONException e) {

            return null;

        }

        return json;

    }

    public void setJson(JSONObject object) {

        try {

            title = object.getString("title");
            text = object.getString("text");
            dateDeadLine = new Date(object.getString("dateDeadLine"));

        } catch (JSONException e) {

        }

    }

    public boolean equals(Note obj) {

        if (obj == null) return false;
        return this.id == obj.getId();

    }

    @Override
    public int compareTo(Note o) {

        if (o == null) return -1;

        if (this.equals(o)) return 0;
        if (this.id > o.getId()) return 1;

        return -1;

    }

}