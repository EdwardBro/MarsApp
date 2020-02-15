package com.example.marsapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.marsapp.BottomNavigationViewHelper;
import com.example.marsapp.R;
import com.example.marsapp.SectionsPageAdapter;
import com.example.marsapp.ui.MainActivity;
import com.example.marsapp.ui.NoteActivity;
import com.example.marsapp.ui.fragments.SecretFragment;
import com.example.marsapp.ui.fragments.WeatherDetails;
import com.example.marsapp.ui.fragments.WeatherOverviewFragment;


public class WeatherActivity extends AppCompatActivity {

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_cloud_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dashboard_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_round_wb_sunny_24);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent1 = new Intent(WeatherActivity.this, MainActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.navigation_notes:
                        Intent intent2 = new Intent(WeatherActivity.this, NoteActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.navigation_weather:

                        break;
                }


                return false;
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new WeatherOverviewFragment());
        adapter.addFragment(new WeatherDetails());
        adapter.addFragment(new SecretFragment());
        viewPager.setAdapter(adapter);
    }
}
