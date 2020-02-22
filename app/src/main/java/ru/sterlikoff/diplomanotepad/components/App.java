package ru.sterlikoff.diplomanotepad.components;

import android.app.Application;

public class App extends Application {

    public static final int RESULT_NEW_NOTE = 100;
    public static final int RESULT_UPDATE_NOTE = 101;

    private static NoteRepository noteRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        noteRepository = new FileNoteRepository(this);

    }

    public static NoteRepository getNoteRepository() {
        return noteRepository;
    }

}