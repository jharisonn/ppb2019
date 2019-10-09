package com.tc.notepad;

import android.content.SharedPreferences;

public class SavedData {
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private static SavedData INSTANCE = null;

    private SavedData(SharedPreferences prefs){
        this.prefs = prefs;
        this.editor = prefs.edit();
    }

    public static synchronized SavedData getInstance(SharedPreferences prefs){
        if(INSTANCE==null){
            INSTANCE = new SavedData(prefs);
        }

        return INSTANCE;
    }

    public void saveData(String data){
        editor.putString("DATA",data).commit();
    }

    public String getData(){
        String data = prefs.getString("DATA",null);

        return data;
    }

}
