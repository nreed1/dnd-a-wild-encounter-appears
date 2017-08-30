package com.example.nreed.awildencounterappears;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nreed.awildencounterappears.Classes.Calculators.XPCalculator;
import com.example.nreed.awildencounterappears.Classes.DataAdapters.DatabaseHelpers.DatabaseHelper;
import com.example.nreed.awildencounterappears.Classes.DataAdapters.GroupDataAdapter;
import com.example.nreed.awildencounterappears.Classes.DataAdapters.MonsterDataAdapter;
import com.example.nreed.awildencounterappears.Classes.DataAdapters.PlayerDataAdapter;
import com.example.nreed.awildencounterappears.Classes.Helpers.BitmapHelper;
import com.example.nreed.awildencounterappears.Classes.MonsterMultiplier;
import com.example.nreed.awildencounterappears.Classes.Objects.DifficultyEnum;
import com.example.nreed.awildencounterappears.Classes.Objects.Group;
import com.example.nreed.awildencounterappears.Classes.Objects.Lists.MonsterList;
import com.example.nreed.awildencounterappears.Classes.Objects.Monster;
import com.example.nreed.awildencounterappears.Classes.Objects.PlayerCharacter;
import com.example.nreed.awildencounterappears.Fragments.GroupFragment;
import com.example.nreed.awildencounterappears.Fragments.MonsterFragment;
import com.example.nreed.awildencounterappears.Fragments.PlayerCharacterFragment;
import com.example.nreed.awildencounterappears.Fragments.PlayerListFragment;
import com.example.nreed.awildencounterappears.Fragments.RandomEncounterFragment;
import com.example.nreed.awildencounterappears.Fragments.SettingsFragment;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MonsterFragment.OnListFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener,
        RandomEncounterFragment.OnFragmentInteractionListener,
        GroupFragment.OnListFragmentInteractionListener,
        PlayerCharacterFragment.OnListFragmentInteractionListener,
        PlayerListFragment.OnListFragmentInteractionListener{

    public static DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setMainBackground();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        try {
            databaseHelper = DatabaseHelper.GetInstance(getApplicationContext());
            databaseHelper.createDataBase();
            databaseHelper.openDataBase();
        }catch (Exception ex){

        }

        startRandomEncounterFragment();
    }
    private void setMainBackground() {
        Bitmap bitmap = BitmapHelper.getBitmap(this, R.drawable.ic_skulls);

        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
        bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

        DrawerLayout constraintLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        constraintLayout.setBackground(bitmapDrawable);
    }

    private void setNavDrawerBackground(){
        Bitmap bitmap = BitmapHelper.getBitmap(this, R.drawable.ic_skulls);

        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
        bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

        DrawerLayout constraintLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        constraintLayout.setBackgroundColor(Color.GREEN);
        constraintLayout.setBackground(bitmapDrawable);
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
        getMenuInflater().inflate(R.menu.main_activity, menu);
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

        if (id == R.id.nav_random) {
            startRandomEncounterFragment();

        } else if (id == R.id.nav_groups) {
            startGroupFragment();
        } else if (id == R.id.nav_saved_encounter) {

        } else if (id == R.id.nav_settings) {
            startSettingsFragment();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startSettingsFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SettingsFragment settingsFragment = new SettingsFragment();
        fragmentTransaction.replace(R.id.content, settingsFragment, "Settings");
        fragmentTransaction.commit();
    }

    private void startRandomEncounterFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RandomEncounterFragment randomEncounterFragment = new RandomEncounterFragment();
        fragmentTransaction.replace(R.id.content, randomEncounterFragment, "RandomEncounter");
        fragmentTransaction.commit();
    }

    private void startGroupFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        GroupFragment groupFragment = new GroupFragment();
        Bundle b = new Bundle();
        GroupDataAdapter groupDataAdapter = new GroupDataAdapter();
        b.putParcelableArrayList("grouplist", new ArrayList<Group>(groupDataAdapter.getGroups()));
        groupFragment.setArguments(b);
        fragmentTransaction.replace(R.id.content, groupFragment, "GroupFragment");
        fragmentTransaction.commit();
    }

    private void startPlayerGroupFragment(int groupId){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PlayerListFragment playerListFragment = new PlayerListFragment();
        Bundle b = new Bundle();
        PlayerDataAdapter playerDataAdapter = new PlayerDataAdapter();
        b.putParcelableArrayList("playerlist", new ArrayList<PlayerCharacter>(playerDataAdapter.getPlayersByGroup(groupId)));
        playerListFragment.setArguments(b);
        fragmentTransaction.replace(R.id.content, playerListFragment, "PlayerGroupFragment");
        fragmentTransaction.commit();
    }

    private void startPlayerFragment(int playerId){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PlayerCharacterFragment playerCharacterFragment= new PlayerCharacterFragment();
        Bundle b = new Bundle();
        PlayerDataAdapter playerDataAdapter = new PlayerDataAdapter();
        b.putParcelable("player", playerDataAdapter.getPlayerById(playerId));
        playerCharacterFragment.setArguments(b);
        fragmentTransaction.replace(R.id.content, playerCharacterFragment, "PlayerGroupFragment");
        fragmentTransaction.commit();
    }


    @Override
    public void onListFragmentInteraction(Monster item) {

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(Group item) {
        if(item != null){
            startPlayerGroupFragment(item.getGroupId());
        }
    }

    @Override
    public void onListFragmentInteraction(PlayerCharacter item) {

    }

    @Override
    public void onPlayerListInteraction(PlayerCharacter item) {
        if(item != null){
            startPlayerFragment(item.getId());
        }
    }
}
