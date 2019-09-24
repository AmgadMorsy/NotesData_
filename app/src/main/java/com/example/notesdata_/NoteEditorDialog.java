package com.example.notesdata_;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class NoteEditorDialog extends DialogFragment {

    public static final String EXTRA_NAME = "edu.nmhu.dc_fragments";
    public static final String EXTRA_NAME1 = "edu.nmhu.dc_fragments1";
    public static final String EXTRA_NAME2 = "edu.nmhu.dc_fragments2";

    private EditText nameText;
    private EditText dateText;
    private EditText descText;

    int position;

    @NonNull
    @Override

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //return super.onCreateDialog(savedInstanceState);

       // position = getArguments().getInt("position");
        Note note = NotesData.getInstance(null).getNoteList().get(position);

        nameText = new EditText(getActivity());
        //nameText.setHint("Enter Name");
        //nameText.setText(note.getName());
        dateText = new EditText(getActivity());
        //dateText.setHint("Enter Date");
        //
        descText = new EditText(getActivity());
        //descText.setHint("Enter Description");
        //descText.setText(note.getDesc());
        dateText.setText(note.getDate());

        if(note.getDesc().equals("empty")&& note.getName().equals("New")) {
            nameText.setHint("Enter Name");
            descText.setHint("Enter Desc");
        }
        else {
            nameText.setText(note.getName());
            descText.setText(note.getDesc());}

        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(Color.WHITE);

        linearLayout.addView(nameText);
        linearLayout.addView(dateText);
        linearLayout.addView(descText);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Modify Note");
        alertDialog.setView(linearLayout);
        alertDialog.setPositiveButton("Done", doneClickedListener);
        alertDialog.setNegativeButton("Cancel", null);

        return alertDialog.show();
    }

    private void sendResult(int resultCode) {
        if(getTargetFragment() == null) {
            // target not set up, do nothing
        } else {
            Intent i = new Intent();
            i.putExtra(EXTRA_NAME, nameText.getText().toString());
            getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);

            i.putExtra(EXTRA_NAME1, dateText.getText().toString());
            getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);

            i.putExtra(EXTRA_NAME2, descText.getText().toString());
            getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
        }
    }

    private DialogInterface.OnClickListener doneClickedListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            //Log.d("NotedEditorDialog", nameText.getText().toString());
            //sendResult(Activity.RESULT_OK);
            NotesData.getInstance(null).getNoteList().get(position).setName(nameText.getText().toString());
            NotesData.getInstance(null).getNoteList().get(position).setDate(dateText.getText().toString());
            NotesData.getInstance(null).getNoteList().get(position).setDesc(descText.getText().toString());
            NotesData.getInstance(null).refreshNotes();
        }
    };
}
