package com.example.marsapp.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import com.example.marsapp.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteRoomDatabase extends RoomDatabase {

    private final static String NAME_DATABASE = "note_database";
    public abstract NoteDao noteDao();
    private static volatile NoteRoomDatabase INSTANCE;

//    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(final SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE Employee ADD COLUMN birthday INTEGER DEFAULT 0 NOT NULL");
//        }
//    };



    public static NoteRoomDatabase getDatabase(final Context context) {
        if ( INSTANCE == null) {
            synchronized (NoteRoomDatabase.class) {
                if( INSTANCE == null) {
                    //Creating new database
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NoteRoomDatabase.class, NAME_DATABASE).build();
                }
            }
        }
        return INSTANCE;
    }
}
