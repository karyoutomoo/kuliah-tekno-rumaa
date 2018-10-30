package com.itsjustfaiq.rumaa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] ARSITEKIMAGES= {R.drawable.rk, R.drawable.hamish, R.drawable.ahmaddjuhara,R.drawable.andramatin,R.drawable.baskorotedjo,R.drawable.budipradono};
    private static final Integer[] DESAINIMAGES={R.drawable.rumah2,R.drawable.rumah3,R.drawable.rumah4,R.drawable.rumah5,R.drawable.rumah6,R.drawable.rumah7};
    private ArrayList<Integer> ArsiImagesArray = new ArrayList<Integer>();
    private ArrayList<Integer> DesainImagesArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button fab = (Button) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setupSliderArsi();
        setupSliderDesain();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.myaccount) {
            Intent goToProfile = new Intent(MainActivity.this, Profile.class);
            startActivity(goToProfile);
        } else if (id == R.id.feed_customer) {
            Intent goToProfile = new Intent(MainActivity.this, FeedCustomerActivity.class);
            startActivity(goToProfile);

        } else if (id == R.id.contactus) {

        } else if (id == R.id.help) {

        } else if (id == R.id.nav_feed) {
            Intent inten = new Intent(MainActivity.this, FeedActivity.class);
            startActivity(inten);

        } else if (id == R.id.nav_account) {
            Intent intent = new Intent(MainActivity.this, ArsitekAccActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_fortofolio) {
            Intent intent = new Intent(MainActivity.this, FortofolioActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_history) {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupSliderArsi() {
        for(int i=0;i<ARSITEKIMAGES.length;i++)
            ArsiImagesArray.add(ARSITEKIMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.pagerArsi);


        mPager.setAdapter(new SlidingImageArsitekAdapter(MainActivity.this,ArsiImagesArray));

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicatorArsi);
        indicator.notifyDataSetChanged();
        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =ARSITEKIMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }

    private void setupSliderDesain() {
        for(int i=0;i<DESAINIMAGES.length;i++)
            DesainImagesArray.add(DESAINIMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.pagerDesain);


        mPager.setAdapter(new SlidingImageDesainAdapter(MainActivity.this,DesainImagesArray));

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicatorDesain);
        indicator.notifyDataSetChanged();
        indicator.setViewPager(mPager);

        NUM_PAGES =DESAINIMAGES.length;
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(5 * density);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
    }
}
