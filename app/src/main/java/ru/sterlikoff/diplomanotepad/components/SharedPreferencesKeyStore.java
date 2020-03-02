package ru.sterlikoff.diplomanotepad.components;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesKeyStore implements KeyStore {

    private final String pinSalt = "diploma_notepad_salt";
    private final String pinVarName = "pinHash";

    private SharedPreferences pref;

    public SharedPreferencesKeyStore(Context context) {
        this.pref = context.getSharedPreferences("App", Context.MODE_PRIVATE);
    }

    @Override
    public boolean hasPin() {
        String currentPin = this.pref.getString(pinVarName, null);
        return currentPin != null;
    }

    @Override
    public boolean checkPin(String pin) {
        return Utils.md5(pin + pinSalt).equals(this.pref.getString(pinVarName, ""));
    }

    @Override
    public void setPin(String pin) {

        this.pref.edit()
                .putString(pinVarName, Utils.md5(pin + pinSalt))
                .apply();

    }
}
