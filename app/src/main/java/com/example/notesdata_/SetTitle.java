package com.example.notesdata_;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
public class SetTitle extends DialogFragment {

    public static final String EXTRA_NAME = "edu.nmhu.dc_fragments4";
    private EditText newTitle;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Context context = getActivity();
        LinearLayout linearlayout = new LinearLayout(context);
        linearlayout.setOrientation(LinearLayout.HORIZONTAL);
        linearlayout.setBackgroundColor(Color.WHITE);
        newTitle = new EditText(context);
        newTitle.setHint("Enter Title");
        linearlayout.addView(newTitle);
        return linearlayout;


    }
}
