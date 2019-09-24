package com.example.notesdata_;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class NoteFragment extends Fragment {

    private static int EDITOR_REQUEST = 1;

    private TextView nameView;
    private TextView dateView;
    private TextView descView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        //Fragments must belong to an activity
        // so we can ask for this fragment's activity for the context
        Context context = getActivity();
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(Color.WHITE);
        int size = 20;
        nameView = new TextView(context);
        nameView.setText("Name");
        nameView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        nameView.setTextColor(Color.BLUE);
        dateView = new TextView(context);
        dateView.setText("Date");
        dateView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        dateView.setTextColor(Color.RED);
        descView = new TextView(context);
        descView.setText("Description");
        descView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        descView.setTextColor(Color.BLACK);


        Button editButton = new Button(context);
        editButton.setText("Edit");
        editButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        editButton.setOnClickListener(editClickedListener);





        LinearLayout dateLayout = new LinearLayout(context);

        dateLayout.setOrientation(LinearLayout.VERTICAL);
        dateLayout.setBackgroundColor(Color.WHITE);
        LinearLayout descLayout = new LinearLayout(context);
        descLayout.setOrientation(LinearLayout.VERTICAL);
        descLayout.setBackgroundColor(Color.WHITE);
        linearLayout.addView(nameView);
        dateLayout.addView(dateView);
        descLayout.addView(descView);
        linearLayout.addView(dateLayout);
        linearLayout.addView(descLayout);
        linearLayout.addView(editButton);
        return linearLayout; }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == EDITOR_REQUEST) { // Request code from note editor dialog. I said it was 1 earlier
            if (resultCode == Activity.RESULT_OK) { // result code means they clicked ok
                nameView.setText(data.getStringExtra(NoteEditorDialog.EXTRA_NAME));
                dateView.setText(data.getStringExtra(NoteEditorDialog.EXTRA_NAME1));
                descView.setText(data.getStringExtra(NoteEditorDialog.EXTRA_NAME2));
            }
        }
    }
    private void showDialog() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        NoteEditorDialog noteEditorDialog = new NoteEditorDialog();
        noteEditorDialog.setTargetFragment(this, EDITOR_REQUEST);
        noteEditorDialog.show(fragmentManager, "DIALOG_NOTE_EDITOR"); }
    private View.OnClickListener editClickedListener = new View.OnClickListener(){@Override public void onClick(View view) { showDialog();
        }
    };
}
