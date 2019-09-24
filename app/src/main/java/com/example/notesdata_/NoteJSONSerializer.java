package com.example.notesdata_;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class NoteJSONSerializer {
    private Context mContext;
    private String mFilename;

    public NoteJSONSerializer(Context c,String filename){
        mContext = c;
        mFilename = filename;
    }

    //old
    public void loadNotes()
            throws IOException, JSONException {
        byte[] bytes = readDataFile();
        ArrayList<Note> notes = NotesData.getInstance(null).getNoteList();

        String jsonString = new String(bytes, "UTF-8");
        try {//try to turn bytes into JSON
            JSONArray jsonArray = (JSONArray) new JSONTokener(jsonString).nextValue();
            //now you should have a json array
            //step through each object in array and pass to note  constructor
            for (int i = 0; i < jsonArray.length(); i++) {
                notes.add(new Note(jsonArray.getJSONObject(i)));
            }
        } catch (Exception e) {
            throw e;
        }
    }


    private byte[] readDataFile()
            throws IOException, JSONException{

        File file = new File (mContext.getFilesDir(),mFilename);
        int lenght = (int)file.length();
        byte[] bytes = new byte [lenght];

        FileInputStream fileInputStream;
        try{
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytes);
            fileInputStream.close();

        }catch (Exception e){
            throw e;
        }return bytes;}

    public void saveNotes(ArrayList<Note>notes)
            throws JSONException ,IOException {

    }
}





