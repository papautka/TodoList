package com.uteev.todolist;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// exportSchema = false -- чтобы хранить историю всех версий БД
@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class realNoteDataBase extends RoomDatabase {

    private static realNoteDataBase instance = null;
    private static final String DB_NAME = "notes.db";

    public static realNoteDataBase getInstance(Application application) {
        if(instance == null) {
            instance = Room.databaseBuilder(
                    application,
                    realNoteDataBase.class,
                    DB_NAME
            )
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract NotesDao notesDao();

}
