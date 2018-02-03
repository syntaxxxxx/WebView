package com.fiqri.idnapps;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.sax.StartElementListener;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.fiqri.idnapps.fragment.HomeFragment;
import com.fiqri.idnapps.fragment.RegisterFragment;
import com.fiqri.idnapps.fragment.ScheduleFragment;
import com.fiqri.idnapps.fragment.TrainingFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //TODO menampung parameter
    String nama = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action",null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //TODO cek status login
        FormSessionActivity.SessionPreference mSession = new FormSessionActivity.SessionPreference(MainActivity.this);
        //TODO get nama from sharedpreference
        nama = mSession.getNama();
        if (nama == null || TextUtils.isEmpty(nama)) {
            startActivity(new Intent(MainActivity.this, FormSessionActivity.class));
            finish();

        } else {

            //TODO ketika fragment belum pernah terpanggil
            if (savedInstanceState == null) {
                HomeFragment mFragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mFragment).commit();
            }

            //TODO memberikan aksi di alamat pada navHeader
            View mView = navigationView.getHeaderView(0);
            final TextView mAlamat = mView.findViewById(R.id.textLink);
            mAlamat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String alamat = mAlamat.getText().toString();
                    Uri uri = Uri.parse(alamat);
                    Intent mIntentLink = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(mIntentLink);
                }
            });
        }

        fab.setVisibility(View.GONE);
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
            //TODO untuk mengubah bahasa, lari kepengaturan bahasa

            Intent mIntentSetting = new Intent (Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntentSetting);
            return true;
        } else if (id == R.id.action_logout) {
            FormSessionActivity.SessionPreference mSession = new FormSessionActivity.SessionPreference(MainActivity.this);
            //TODO logout
            mSession.logout();
            //TODO pindah ke form session
            startActivity(new Intent(MainActivity.this, FormSessionActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    // ini yang diatur pertama

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //TODO set ketika item diklik
        Fragment mFragment = null;

        if (id == R.id.nav_home) {
            //TODO ketika home diklik
            mFragment = new HomeFragment();
        } else if (id == R.id.nav_schedule) {
            //TODO ketika schedule diklik
            mFragment = new ScheduleFragment();
        } else if (id == R.id.nav_training) {
            //TODO ketika training diklik
            mFragment = new TrainingFragment();
        } else if (id == R.id.nav_register) {
            //TODO ketika register diklik
            mFragment = new RegisterFragment();
        } else if (id == R.id.nav_contact) {
            //TODO ketika kontak di klik
            Intent mIntentContact = new Intent(Intent.ACTION_DIAL);
            mIntentContact.setData(Uri.parse("tel:081908191001"));
            startActivity(mIntentContact);
        } else if (id == R.id.nav_email) {
            //TODO ketika email di klik
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:info@idn.id"));
            intent.putExtra(Intent.EXTRA_SUBJECT, "From IDN APPS, " + nama);
            intent.putExtra(Intent.EXTRA_TEXT, "Hallo i am " + nama);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }

        if (mFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mFragment).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
