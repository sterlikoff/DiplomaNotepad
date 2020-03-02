package ru.sterlikoff.diplomanotepad.components;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import ru.sterlikoff.diplomanotepad.models.Note;

public class FileNoteRepository implements NoteRepository {

    private Context context;
    private SharedPreferences pref;

    private final String lastIdKey = "lastNoteId";

    private Map<Integer, Note> map;

    public FileNoteRepository(Context context) {

        this.context = context;
        this.pref = context.getSharedPreferences("App", Context.MODE_PRIVATE);
        map = new HashMap<>();

    }

    private int getLastId() {
        return this.pref.getInt(lastIdKey, 0);
    }

    private int getNextId() {
        return getLastId() + 1;
    }

    private String getFileNameById(int id) {
        return id + ".json";
    }

    @Override
    public Note findById(int id) {

        if (map.isEmpty()) findAll();
        return map.get(id);

    }

    @Override
    public Map<Integer, Note> findAll() {

        int lastId = getLastId();

        for (int currentId = lastId; currentId > 0; currentId--) {

            try {

                InputStreamReader reader = new InputStreamReader(context.openFileInput(getFileNameById(currentId)));
                BufferedReader bufferedReader = new BufferedReader(reader);

                boolean hasData = true;
                StringBuilder content = new StringBuilder();

                while (hasData) {

                    String line = bufferedReader.readLine();
                    hasData = line != null && !line.isEmpty();

                    if (hasData) {
                        content.append(line);
                        content.append("\n");
                    }

                }

                JSONObject object = new JSONObject(content.toString());

                Note model = new Note(currentId);
                model.setJson(object);

                map.put(currentId, model);

            } catch (Exception e) {

            }

        }

        return map;

    }

    @Override
    public boolean save(Note model) {

        JSONObject content = model.getJson();
        if (content == null) return false;

        int id = model.getId() > 0 ? model.getId() : getNextId();
        File file = new File(context.getFilesDir(), getFileNameById(id));

        FileWriter writer;

        try {

            writer = new FileWriter(file);

            writer.write(content.toString());
            writer.close();

        } catch (IOException e) {
            return false;
        }

        model.setId(id);

        this.pref.edit().putInt(lastIdKey, id).apply();
        map.put(id, model);

        return true;

    }

    @Override
    public boolean deleteById(int id) {

        File file = new File(context.getFilesDir(), getFileNameById(id));
        boolean ok = file.delete();
        if (!ok) return false;
        map.remove(id);

        return true;

    }

}
