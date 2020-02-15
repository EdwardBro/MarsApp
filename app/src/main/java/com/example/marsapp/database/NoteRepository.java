package com.example.marsapp.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.marsapp.Note;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NoteRepository {

    private NoteRoomDatabase mAppDatabase;
    private NoteDao mNoteDao;
    private LiveData<List<Note>> mNotes;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public NoteRepository (Context context) {
        mAppDatabase = NoteRoomDatabase.getDatabase(context);
        mNoteDao = mAppDatabase.noteDao();
        mNotes = mNoteDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return mNotes;
    }

    public void insert(final Note note) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mNoteDao.insertNote(note);
            }
        });
    }
    public void update(final Note note) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mNoteDao.updateNote(note);
            }
        });
    }
    public void delete(final Note note) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mNoteDao.deleteNote(note);
            }
        });
    }

    public void deleteAll(final List<Note> notes){
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mNoteDao.deleteAllNotes(notes);
            }
        });
    }
}
