package com.example.marsapp.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marsapp.BottomNavigationViewHelper;
import com.example.marsapp.R;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * made by EdwardBro
 */

public class MainActivity extends AppCompatActivity {

    /*    private SectionsPageAdapter mSectionsPageAdapter;

        private ViewPager mViewPager;*/

    TextView tvMarsDay;
    WeatherActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.text_view_date);
        textViewDate.setText(currentDate);

        tvMarsDay = findViewById(R.id.text_view_date2);

        viewModel = ViewModelProviders.of(this).get(WeatherActivityViewModel.class);

        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG)
                        .show();
            }
        });

        viewModel.getSolKey().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                tvMarsDay.setText(viewModel.getSolKey().toString());
            }
        });

        //      mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());


        // Set up the ViewPager with the sections adapter.
//        mViewPager = (ViewPager) findViewById(R.id.container);
//        setupViewPager(mViewPager);

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
                        break;

                    case R.id.navigation_notes:
                        Intent intent2 = new Intent(MainActivity.this, NoteActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.navigation_weather:
                        Intent intent3 = new Intent(MainActivity.this, WeatherActivity.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });
    }
}