package com.uteev.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes;
    private FloatingActionButton buttonAddNote;
//    private emitationDataBase DataBase = emitationDataBase.getInstance();
    private realNoteDataBase realNoteDataBase;
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realNoteDataBase = realNoteDataBase.getInstance(getApplication());
        initViews();

        recyclerAdapter = new RecyclerAdapter();
//        recyclerAdapter.setOnNoteClickListener(new RecyclerAdapter.OnNoteClickListener() {
//            @Override
//            public void onNoteClick(Note note) {
//                DataBase.remove(note.getId());
//                showNotes();
//            }
//        });

        recyclerViewNotes.setAdapter(recyclerAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Note note = recyclerAdapter.getNotes().get(position);
//                        DataBase.remove(note.getId());
                        realNoteDataBase.notesDao().remove(note.getId());
                        showNotes();
                    }
                }
        );
        itemTouchHelper.attachToRecyclerView(recyclerViewNotes);


        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNextScreen();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showNotes();

    }

    private void initViews() {
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
        buttonAddNote = findViewById(R.id.buttonAddNote);
    }

    private void showNotes() {
//       recyclerAdapter.setNotes(DataBase.returnArrayNoteNew());
        recyclerAdapter.setNotes(realNoteDataBase.notesDao().getNotes());
    }

    private void launchNextScreen() {
        Intent intent = enterNoteActivity.newIntent(this);
        startActivity(intent);
    }

}