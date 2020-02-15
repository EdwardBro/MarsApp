package com.example.marsapp.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.marsapp.Note;
import com.example.marsapp.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private List<Note> mNotes;

    public NoteAdapter(List<Note> mNotes) {
        this.mNotes = mNotes;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview, viewGroup, false);
        NoteAdapter.ViewHolder viewHolder = new NoteAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder viewHolder, int i) {
        Note note = mNotes.get(i);
        viewHolder.titleView.setText(note.getNoteName());
        viewHolder.textView.setText(note.getNoteText());
        viewHolder.statusView.setText(note.getNoteStatus());
        viewHolder.dateView.setText(note.getNoteEdited());
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public void swapList (List<Note> newList) {
        mNotes = newList;
        if(newList != null) {
            this.notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleView, textView, statusView, dateView;

        public ViewHolder(View itemView){
            super(itemView);
            titleView = itemView.findViewById(R.id.titleView);
            textView = itemView.findViewById(R.id.textView);
            statusView = itemView.findViewById(R.id.statusView);
            dateView = itemView.findViewById(R.id.dateView);
        }
    }
}
