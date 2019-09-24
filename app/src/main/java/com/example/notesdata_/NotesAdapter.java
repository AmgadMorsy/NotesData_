package com.example.notesdata_;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private static int NAME_ID = View.generateViewId();
    private static int DATE_ID = View.generateViewId();
    private static int DESC_ID = View.generateViewId();

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nameView;
        TextView dateView;
        TextView descView;

        int position = -1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameView = itemView.findViewById(NAME_ID);
            dateView = itemView.findViewById(DATE_ID);
            descView = itemView.findViewById(DESC_ID);
        }

        private void showDialog() {

            Bundle arguments = new Bundle();
            arguments.putInt("position", position);

            FragmentManager fragmentManager = ((FragmentActivity)getmContext()).getSupportFragmentManager();
            NoteEditorDialog noteEditorDialog = new NoteEditorDialog();
            //noteEditorDialog.setTargetFragment(this, EDITOR_REQUEST);
            // pass that bundle to the dialog fragment
            noteEditorDialog.setArguments(arguments);
            noteEditorDialog.show(fragmentManager, "DIALOG_NOTE_EDITOR");
        }

        private View.OnClickListener editClickedListener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Log.d("NoteFragment", "edit clicked");
                showDialog();
            }
        };

        private View.OnClickListener deleteClickedListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotesData.getInstance(null).getNoteList().remove(position);
                NotesData.getInstance(null).refreshNotes();
            }
        };

    }

    private Context mContext;

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setBackgroundColor(Color.WHITE);
        int size = 20;
        TextView nameView = new TextView(mContext);
        nameView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        TextView dateView = new TextView(mContext);
        dateView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        TextView descView = new TextView(mContext);
        descView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        nameView.setId(NAME_ID);
        dateView.setId(DATE_ID);
        descView.setId(DESC_ID);

        Button editButton = new Button(mContext);
        editButton.setText("Edit");

        Button deleteButton = new Button(mContext);
        deleteButton.setText("Delete");

        LinearLayout buttonLL = new LinearLayout (mContext);
        buttonLL.setOrientation(LinearLayout.HORIZONTAL);
        buttonLL.setLayoutParams(linearLayout.getLayoutParams());
        buttonLL.addView(editButton);
        buttonLL.addView(deleteButton);


        linearLayout.addView(nameView);
        linearLayout.addView(dateView);
        linearLayout.addView(descView);
        linearLayout.addView(buttonLL);

        ViewHolder vh = new ViewHolder(linearLayout);
        editButton.setOnClickListener(vh.editClickedListener);
        deleteButton.setOnClickListener(vh.deleteClickedListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {

        Note note = NotesData.getInstance(mContext).getNoteList().get(position);

        holder.nameView.setText(note.getName());
        holder.dateView.setText(note.getDate());
        holder.descView.setText(note.getDesc());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return NotesData.getInstance(mContext).getNoteList().size();
    }


}
