package com.example.marsapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.marsapp.BottomNavigationViewHelper;
import com.example.marsapp.Note;
import com.example.marsapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditActivity extends AppCompatActivity {

    private Spinner spinner;
    private TextView textViewTitle, textViewText;
    private boolean editingMode;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinner = findViewById(R.id.spinner);
        textViewTitle = findViewById(R.id.editTextTitle);
        textViewText = findViewById(R.id.editTextText);

        String[] items = new String[]{"In progress", "Stopped", "Done"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);

        Intent inIntent = getIntent();
        editingMode = inIntent.getBooleanExtra(NoteActivity.EDIT, false);
        if(editingMode){
            note = inIntent.getParcelableExtra(NoteActivity.EXTRA_NOTE);
            textViewTitle.setText(note.getNoteName());
            textViewText.setText(note.getNoteText());
            spinner.setSelection(adapter.getPosition(note.getNoteStatus()));
            getSupportActionBar().setTitle(R.string.title_edit);
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textTitle = textViewTitle.getText().toString();
                String textText = textViewText.getText().toString();
                String textStatus = spinner.getSelectedItem().toString();
                if(TextUtils.isEmpty(textTitle)){
                    Snackbar.make(view, "Please set a title of your note", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if(TextUtils.isEmpty(textText)){
                    Snackbar.make(view, "Please set a text of your note", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    if(editingMode){
                        note.setAll(textTitle, textText, textStatus, new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
                    } else {
                        note = new Note(textTitle, textText, textStatus, new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
                    }
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(NoteActivity.EDIT, editingMode);
                    resultIntent.putExtra(NoteActivity.EXTRA_NOTE, note);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent1 = new Intent(EditActivity.this, MainActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.navigation_notes:
                        Intent intent2 = new Intent(EditActivity.this, NoteActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.navigation_weather:
                        Intent intent3 = new Intent(EditActivity.this, WeatherActivity.class);
                        startActivity(intent3);
                        break;

                }
                return false;
            }
        });
    }
}
