package ru.sterlikoff.diplomanotepad.components;

import android.app.Application;

public class App extends Application {

    public static final int RESULT_NEW_NOTE = 100;
    public static final int RESULT_UPDATE_NOTE = 101;
    public static final int RESULT_PIN_CODE = 102;

    private static NoteRepository noteRepository;
    private static KeyStore keyStore;

    @Override
    public void onCreate() {

        super.onCreate();

        noteRepository = new FileNoteRepository(this);
        keyStore = new SharedPreferencesKeyStore(this);

    }

    public static NoteRepository getNoteRepository() {
        return noteRepository;
    }

    public static KeyStore getKeyStore() {
        return keyStore;
    }

}