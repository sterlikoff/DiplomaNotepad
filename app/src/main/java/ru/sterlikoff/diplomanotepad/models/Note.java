package ru.sterlikoff.diplomanotepad.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Date;

public class Note implements Comparable<Note> {

    private int id;

    public String title;
    public String text;

    public Date dateDeadLine;

    public Note() {

    }

    public Note(int id) {
        this.id = id;
    }

    public boolean validate() {
        return !text.isEmpty();
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
    public int compareTo(@Nullable Note o) {

        if (o == null) return -1;

        if (o.dateDeadLine == null || this.dateDeadLine == null) {

            if (this.equals(o)) return 0;
            if (this.id > o.getId()) return 1;

            return -1;

        }

        if (o.dateDeadLine.equals(this.dateDeadLine)) return 0;
        if (o.dateDeadLine.before(this.dateDeadLine)) return 1;

        return -1;

    }

}