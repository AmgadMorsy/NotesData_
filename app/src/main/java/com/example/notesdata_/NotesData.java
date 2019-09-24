package com.example.notesdata_;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class NotesData { // Singleton class of all note data
    // Listener Interface
    public interface NotesDataUpdatedListener {
        public void updateNotesDependents();
    }
    // an instance of the listener
    public NotesDataUpdatedListener listener;

    private ArrayList<Note> mNotes;
    private static NotesData sNotesData;
    private Context mAppContext;
    private NotesData(Context context) {
        mAppContext = context;
        mNotes = new ArrayList<Note>();
        listener = null; }
    public ArrayList<Note> getNoteList() {
        return mNotes;
    }

    public static NotesData getInstance(@Nullable Context c) {
        if(sNotesData == null){ // if notes data never created
            // create one and only instance of notes data
            Log.d("NotesData", "only instance");
            sNotesData = new NotesData(c.getApplicationContext()); }
        // return same instance of notes data every time.
        return sNotesData; }

    public void setListener(NotesDataUpdatedListener notesDataUpdatedListener) {
        listener = notesDataUpdatedListener; }

    public void refreshNotes() {
        if(listener != null)
            listener.updateNotesDependents();
    }
}
