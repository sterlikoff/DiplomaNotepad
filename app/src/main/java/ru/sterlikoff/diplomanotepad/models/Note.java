package ru.sterlikoff.diplomanotepad.models;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Date;

public class Note {

    private int id;

    public String title;
    public String text;

    private Date dateCreated;
    public Date dateDeadLine;

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

}