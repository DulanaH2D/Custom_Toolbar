package com.nvision.customtoolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.nvision.customtoolbar.fragments.ContactUsFragment;
import com.nvision.customtoolbar.fragments.HomeFragment;
import com.nvision.customtoolbar.fragments.MyAccountFragment;
import com.nvision.customtoolbar.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Context context;
    private boolean isAllSelected = false;
    //To access fragment methods in MainActivity
    private HomeFragment homeFragment;
    private static final String HOME_FRAGMENT_TAG = "HomeFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        context = this;
        if (savedInstanceState == null) {
            homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, homeFragment)
                    .commit();
        } else {
            homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HOME_FRAGMENT_TAG);
        }

        ImageView deleteIcon = findViewById(R.id.delete);
        ImageView selectAllIcon = findViewById(R.id.select_all);
        Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        // actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        // getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        deleteIcon.setOnClickListener(v -> {
            homeFragment.deleteSelectedItems();
            Toast.makeText(context, "Delete selected items", Toast.LENGTH_SHORT).show();
        });

        selectAllIcon.setOnClickListener(v -> {
            if (isAllSelected) {
                homeFragment.unSelectAllItems();
                Toast.makeText(context, "Unelected All", Toast.LENGTH_SHORT).show();// Unselect all items
                isAllSelected = false;
            } else {
                homeFragment.selectAllItems();
                Toast.makeText(context, "Selected All", Toast.LENGTH_SHORT).show();// Select all items
                isAllSelected = true;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        switch (item.getItemId()) {
            case R.id.nav_home:
                selectedFragment = new HomeFragment();
                Toast.makeText(MainActivity.this, "Clicked Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_account:
                selectedFragment = new MyAccountFragment();
                Toast.makeText(MainActivity.this, "Clicked My Account", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_settings:
                selectedFragment = new SettingsFragment();
                Toast.makeText(MainActivity.this, "Clicked Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_contact_us:
                selectedFragment = new ContactUsFragment();
                Toast.makeText(MainActivity.this, "Clicked Contact Us", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, selectedFragment)
                    .addToBackStack(null)  // Add transaction to the back stack
                    .commit();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //
    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof HomeFragment) {
            homeFragment = (HomeFragment) fragment;
        }
    }

}