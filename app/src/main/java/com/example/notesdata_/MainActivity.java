package com.example.notesdata_;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NotesData.NotesDataUpdatedListener {
    private static String LOGID = "MainActivity";
    private NoteJSONSerializer noteJSONSerializer;
    private Note note = new Note();
    private RecyclerView notesRV;
    private ArrayList<Note> notesArray;

    int position;
    private Object SetTitle;

    @Nullable
    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOGID, "onCreate Called");
        //setContentView(R.layout.activity_main);
        callPrefernces();
        makeData();
        noteJSONSerializer = new NoteJSONSerializer(this, "notes.json");
        try {
            noteJSONSerializer.saveNotes(NotesData.getInstance(this).getNoteList());
        } catch (Exception e) {
            Log.d(LOGID, e.toString());
        }
        notesArray = NotesData.getInstance(this).getNoteList();
        NotesData.getInstance(this).setListener(this);
        notesRV = new RecyclerView(this);
        notesRV.setBackgroundColor(Color.RED);

        final NotesAdapter notesAdapter = new NotesAdapter();
        notesAdapter.setmContext(this);
        notesRV.setAdapter(notesAdapter);
        notesRV.setLayoutManager(new LinearLayoutManager(this));

        Button save = new Button(this);
        save.setText("save");
        save.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        save.setOnClickListener(saveClicked);

        final Button addNoteButton = new Button(this);
        addNoteButton.setText("+");
        addNoteButton.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNotes();
                NotesData.getInstance(null).refreshNotes();
            }
        });


        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        linearLayout.setId(View.generateViewId());


        linearLayout.addView(addNoteButton);
        linearLayout.addView(save);
        linearLayout.addView(notesRV);

        NoteFragment SetTitle = new NoteFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(linearLayout.getId(),SetTitle);
        fragmentTransaction.commit();
        
        setContentView(linearLayout);
    }




    public void addNotes() {


        note.setName("Name");
        note.setDesc("Description");
        notesArray.add(note);
    }
    public void deleteNotes() {
        notesArray.remove(note);
    }
    private void makeData() {
        ArrayList<Note> notes = NotesData.getInstance(this).getNoteList();
        for (int i = 0; i < 10; i++) {
            Note note = new Note();
            note.setName("Note #" + i);
            note.setDesc(String.valueOf(View.generateViewId()));
            notes.add(note);
        }
    }
    private View.OnClickListener saveClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                NoteJSONSerializer noteJSONSerializer = new NoteJSONSerializer(getApplicationContext(), "notes.json");
                noteJSONSerializer.saveNotes(NotesData.getInstance(null).getNoteList());
            } catch (Exception e) {
                Log.d("MainActivity", e.toString());
            }
        }
    };

        /*private void callPrefernces() {
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        String titlePref = prefs.getString("UserTitle", null);
        if (titlePref== (null)) {
            setTitle(captureTitle(prefs));

        } else {
            setTitle(titlePref); } }


        private String captureTitle(SharedPreferences prefs){
        String userInput = "Notebook";
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("UserTitle","Amgad's Notebook");
        editor.commit();
        return userInput;
    }
*/





    private void callPrefernces(){
        setTitle("amgad");
}


    @Override
    protected void onStart(){
        super.onStart();
        Log.d(LOGID,"onStart Called");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d(LOGID,"onStop Called");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(LOGID,"onResume Called");
        try {
            noteJSONSerializer.loadNotes();
        }catch (Exception e){
            Log.d(LOGID, e.toString());
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(LOGID,"onDestroy Called");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d(LOGID,"onPause Called");
        ArrayList<Note>notes = NotesData.getInstance(this).getNoteList();
        try{
            noteJSONSerializer.saveNotes(notes);

        }catch (Exception e){
            Log.d(LOGID, e.toString());
        }
    }
    @Override
    public void updateNotesDependents() {
        NotesAdapter notesAdapter = new NotesAdapter();
        notesAdapter.setmContext(this);
        notesRV.swapAdapter(notesAdapter, true);
    }}



