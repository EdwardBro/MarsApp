package com.example.marsapp.ui;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.marsapp.BottomNavigationViewHelper;
import com.example.marsapp.Note;
import com.example.marsapp.R;
import com.example.marsapp.database.NoteRoomDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NoteActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {

    private List<Note> mNotes;
    private NoteAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private GestureDetector mGestureDetector;
    private NoteRoomDatabase db;
    private NoteViewModel noteViewModel;
    private Executor executor = Executors.newSingleThreadExecutor();
    private ActionBar toolbar;

    public static final int REQUESTCODEADD = 1234;
    public static final int REQUESTCODEEDIT = 4321;
    public static final String EDIT = "editoradd";
    public static final String EXTRA_NOTE = "extranote";

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);


        db = NoteRoomDatabase.getDatabase(this);
        mNotes = new ArrayList<>();
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                mNotes = notes;
                updateUI();
            }
        });

//        initToolbar();
        initFloatingButton();
        initRecyclerView();

        updateUI();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

//        // attaching bottom sheet behaviour - hide / show on scroll
//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
//        layoutParams.setBehavior(new BottomNavigationBehavior());

        // load the store fragment by default
//        toolbar.setTitle("Note");
//        loadFragment(new HomeFragment());


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent1 = new Intent(NoteActivity.this, MainActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.navigation_notes:
                        break;
                    case R.id.navigation_weather:
                        Intent intent3 = new Intent(NoteActivity.this, WeatherActivity.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });
    }


//        private void initToolbar() {
//            Toolbar toolbar = findViewById(R.id.toolbar);
//            setSupportActionBar(toolbar);
//        }

    private void initFloatingButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoteActivity.this, EditActivity.class);
                intent.putExtra(EDIT, false);
                startActivityForResult(intent, REQUESTCODEADD);
            }
        });
    }

    private void initRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                int position = (viewHolder.getAdapterPosition());
                final Note note = mNotes.get(position);
                noteViewModel.delete(note);
                Snackbar snackbar = Snackbar.make(findViewById(R.id.mainContent), "Deleted from list", Snackbar.LENGTH_LONG);

                snackbar.setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        noteViewModel.insert(note);
                    }
                }).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.addOnItemTouchListener(this);
    }

    public void updateUI() {
        if (mAdapter == null) {
            mAdapter = new NoteAdapter(mNotes);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.swapList(mNotes);
        }
    }

    private void deleteAllNotes(final List<Note> notes) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                noteViewModel.deleteAll(notes);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (item.getItemId() == R.id.action_delete_all) {
            deleteAllNotes(mNotes);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (child != null) {
            int mAdapterPosition = recyclerView.getChildAdapterPosition(child);
            if (mGestureDetector.onTouchEvent(motionEvent)) {
                Intent intent = new Intent(NoteActivity.this, EditActivity.class);
                intent.putExtra(EDIT, true);
                intent.putExtra(EXTRA_NOTE, mNotes.get(mAdapterPosition));
                startActivityForResult(intent, REQUESTCODEEDIT);
            }
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Note note = data.getParcelableExtra(EXTRA_NOTE);
            if (requestCode == REQUESTCODEADD) {
                noteViewModel.insert(note);
            } else if (requestCode == REQUESTCODEEDIT) {
                noteViewModel.update(note);
            }
        }
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}

