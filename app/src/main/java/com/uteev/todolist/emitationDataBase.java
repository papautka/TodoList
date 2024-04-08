package com.uteev.todolist;

import java.util.ArrayList;
import java.util.Random;

public class emitationDataBase {
    private ArrayList<Note> notes = new ArrayList<Note>();

    private static emitationDataBase instance = null;

    public static emitationDataBase getInstance() {
        if(instance == null) {
            instance = new emitationDataBase();
        }
        return instance;
    }

    public void DataBase() {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            Note note = new Note(i, "Note" + i, random.nextInt(3));
            notes.add(note);
        }
    }

    public void add(Note note) {
        notes.add(note);
    }

    public void remove(int id) {
        for(int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            if(id == note.getId()) {
                notes.remove(id);
            }
        }
    }

    public ArrayList<Note> returnArrayNoteNew() {
        return new ArrayList<Note>(notes);
    }
}
