package com.example.alexboicko.mygooglemapstest.View;



import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.alexboicko.mygooglemapstest.R;
import com.example.alexboicko.mygooglemapstest.View.Fragments.Fragment_creat_new_time_line;
import com.example.alexboicko.mygooglemapstest.View.Fragments.Fragment_current_time_line;
import com.example.alexboicko.mygooglemapstest.View.Fragments.Fragment_map;
import com.example.alexboicko.mygooglemapstest.View.Fragments.Fragment_show_all_time_line;
import com.example.alexboicko.mygooglemapstest.ViewModel.ViewModelLogic;

public class MainWindow extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private BottomNavigationView mainNavigation;

    private Fragment_creat_new_time_line fragmentCreatNewTimeLine;
    private Fragment_current_time_line fragmentCurrentTimeLine;
    private Fragment_show_all_time_line fragmentShowAllTimeLine;
    private Fragment_map fragmentMap;
    private android.support.v4.app.FragmentTransaction fragmentTransaction;

    private ViewModelLogic viewModelLogic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_schedule);
        mainNavigation = (BottomNavigationView) findViewById(R.id.navigationButton);
        mainNavigation.setOnNavigationItemSelectedListener(this);

        fragmentCurrentTimeLine = new Fragment_current_time_line();
        fragmentCreatNewTimeLine = new Fragment_creat_new_time_line();
        fragmentShowAllTimeLine = new Fragment_show_all_time_line();
        fragmentMap = new Fragment_map();

        //fragmentTransaction.replace(R.id.fragmentLayout,fragmentCurrentTimeLine);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.currentDaySchedule:
                fragmentTransaction.replace(R.id.fragmentLayout,fragmentCurrentTimeLine);
                break;
            case R.id.createSchedule:
                fragmentTransaction.replace(R.id.fragmentLayout,fragmentCreatNewTimeLine);
                break;
            case R.id.scheduleList:
                fragmentTransaction.replace(R.id.fragmentLayout,fragmentShowAllTimeLine);
                break;
            case R.id.map:
                fragmentTransaction.replace(R.id.fragmentLayout,fragmentMap);
                break;
        }
        fragmentTransaction.commit();
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModelLogic = new ViewModelLogic(this);

        Log.d("MY_TAG","ViewModelLogic: " + viewModelLogic);
    }


}
