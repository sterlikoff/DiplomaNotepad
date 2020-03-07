package ru.sterlikoff.diplomanotepad.models;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());

            json.put("title", this.title);
            json.put("text", this.text);
            json.put("dateDeadLine", dateFormat.format(this.dateDeadLine));

        } catch (JSONException e) {

            return null;

        }

        return json;

    }

    public void setJson(JSONObject object) {

        try {

            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());

            this.title = object.getString("title");
            this.text = object.getString("text");

            try {

                this.dateDeadLine = dateFormat.parse(object.getString("dateDeadLine"));

            } catch (ParseException e) {
                e.printStackTrace();
            }

        } catch (JSONException e) {
            e.printStackTrace();
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