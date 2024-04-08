package com.uteev.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class enterNoteActivity extends AppCompatActivity {
    private EditText enterNote;
    private RadioGroup radioGroupThree;
    private RadioButton RadioLow;
    private RadioButton RadioMedium;
    private RadioButton RadioHigh;
    private Button buttonSave;

//    private emitationDataBase DataBase = emitationDataBase.getInstance();
    private realNoteDataBase realNoteDataBase = com.uteev.todolist.realNoteDataBase.getInstance(getApplication());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_note);
        initView();
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    private void initView() {
        enterNote = findViewById(R.id.enterNote);
        radioGroupThree = findViewById(R.id.radioGroupThree);
        RadioLow = findViewById(R.id.RadioLow);
        RadioMedium = findViewById(R.id.RadioMidium);
        RadioHigh = findViewById(R.id.RadioHigh);
        buttonSave = findViewById(R.id.buttonSave);
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, enterNoteActivity.class);
        return intent;
    }

    private void saveNote() {
        String enterNoteStr = enterNote.getText().toString().trim();
        int priority = getPriority();
//        int id = DataBase.returnArrayNoteNew().size();
//        int id = realNoteDataBase.notesDao().getNotes().size();
        Note note = new Note(enterNoteStr, priority);
//        DataBase.add(note);
        realNoteDataBase.notesDao().add(note);

        finish();
    }

    private int getPriority() {
        int priority = -1;
        if(RadioLow.isChecked()) {
            priority = 0;
        }
        if(RadioMedium.isChecked()) {
            priority = 1;
        }
        if(RadioHigh.isChecked()) {
            priority = 2;
        }
        Log.d("getPriority", "priority" + priority);
        return priority;
    }



}