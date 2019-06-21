package in.monte.spsu.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import in.monte.spsu.Home;
import in.monte.spsu.Notice.Notice;
import in.monte.spsu.Notice.ShowNotice;
import in.monte.spsu.R;
import in.monte.spsu.Result.Result;
import in.monte.spsu.TimeTable.TimeTable;

public class MainActivity extends AppCompatActivity implements Notice.OnShowNoticeListener{
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navbar;
    ActionBarDrawerToggle action;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.email);

        setSupportActionBar(toolbar);
        toolbar.setTitle("About SPSU");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(navbar);
            }
        });
        drawer = findViewById(R.id.drawer);
        action = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
        drawer.setDrawerListener(action);
        navbar = findViewById(R.id.navView);

        changeFragment(new Home());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ContactUs.class));
            }
        });

        navbar.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.home:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                drawer.closeDrawers();
                            }
                        }, 50);
                        getSupportActionBar().setTitle("About SPSU");
                        changeFragment(new Home());
                        item.setChecked(true);
                        break;
                    case R.id.notice:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                drawer.closeDrawers();
                            }
                        },50);
                        getSupportActionBar().setTitle("Notice");
                        changeFragment(new Notice());
                        item.setChecked(true);
                        break;
                    case R.id.result:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                drawer.closeDrawers();
                            }
                        },50);
                        getSupportActionBar().setTitle("Result");
                        changeFragment(new Result());
                        item.setChecked(true);
                        break;
                    case R.id.timetable:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                drawer.closeDrawers();
                            }
                        },50);
                        getSupportActionBar().setTitle("Time Table");
                        changeFragment(new TimeTable());
                        item.setChecked(true);
                        break;
                    case R.id.share:
                        Intent share = new Intent(Intent.ACTION_SEND);
                        share.setType("text/plain");
                        share.putExtra(Intent.EXTRA_SUBJECT, "SPSU_APP");
                        share.putExtra(Intent.EXTRA_TEXT, "This APP is Developed by Monu Lakshkar.");
                        startActivity(Intent.createChooser(share,"Share Via: "));
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        action.syncState();
    }

    private void changeFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container,fragment);
        ft.commit();
    }

    @Override
    public void showNotice(int id) {
        ShowNotice sn = new ShowNotice();
        sn.setNotice(id);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container,sn).addToBackStack("monu");
        ft.commit();
    }
}
