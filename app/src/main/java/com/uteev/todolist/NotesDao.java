package com.uteev.todolist;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NotesDao {
    @Query("SELECT * from notes")
    List<Note> getNotes();
    // не нужно здесь указывать ArrayList
    // какую коллекцию будет use room хз


    // старый обект удалится взамен нового
    //(onConflict = OnConflictStrategy.REPLACE)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void add(Note note);


    @Query("DELETE FROM notes where id = :id")
    void remove(int id);
}
