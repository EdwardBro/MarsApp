package com.example.marsapp.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.marsapp.database.NoteRepository;
import com.example.marsapp.Note;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository mRepository;
    private LiveData<List<Note>> mNotes;

    public NoteViewModel(@NonNull Application application){
        super(application);
        mRepository = new NoteRepository(application.getApplicationContext());
        mNotes = mRepository.getAllNotes();
    }

    public LiveData<List<Note>> getNotes() {
        return mNotes;
    }

    public void insert(Note note){
        mRepository.insert(note);
    }
    public void update(Note note) {
        mRepository.update(note);
    }
    public void delete(Note note) {
        mRepository.delete(note);
    }
    public void deleteAll(List<Note> list) {
        mRepository.deleteAll(list);
    }
}
