package com.example.marsapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.Entity;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "note")
public class Note implements Parcelable{

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "noteName")
    private String noteName;

    @ColumnInfo(name = "noteText")
    private String noteText;

    @ColumnInfo(name = "noteStatus")
    private String noteStatus;

    @ColumnInfo(name = "date")
    private String noteEdited;

    @Override
    public String toString() {
        return "Note: " + this.getNoteName();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getNoteStatus() {
        return noteStatus;
    }

    public void setNoteStatus(String noteStatus) {
        this.noteStatus = noteStatus;
    }

    public String getNoteEdited() {
        return noteEdited;
    }

    public void setNoteEdited(String noteEdited) {
        this.noteEdited = noteEdited;
    }

    public void setAll(String name, String text, String status, String editDate){
        this.noteName = name;
        this.noteText = text;
        this.noteStatus = status;
        this.noteEdited = editDate;
    }

    public enum STATUS {
        P("In Progress"),
        S("Stopped"),
        D("Done");

        private final String status;

        STATUS(String text){
            this.status = text;
        }

        @TypeConverter
        public String statusToString() {
            return "";
        }
    }

    public Note(String noteName, String noteText, String noteStatus, String noteEdited) {
        this.noteName = noteName;
        this.noteText = noteText;
        this.noteStatus = noteStatus;
        this.noteEdited = noteEdited;
    }

    protected Note(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        noteName = in.readString();
        noteText = in.readString();
        noteStatus = in.readString();
        noteEdited = in.readString();
    }

    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(noteName);
        dest.writeString(noteText);
        dest.writeString(noteStatus);
        dest.writeString(noteEdited);
    }
}
