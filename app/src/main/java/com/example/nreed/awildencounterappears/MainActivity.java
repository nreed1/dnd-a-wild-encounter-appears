package com.example.nreed.awildencounterappears;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.nreed.awildencounterappears.Classes.Calculators.XPCalculator;
import com.example.nreed.awildencounterappears.Classes.DataAdapters.DatabaseHelpers.DatabaseHelper;
import com.example.nreed.awildencounterappears.Classes.DataAdapters.MonsterDataAdapter;
import com.example.nreed.awildencounterappears.Classes.MonsterMultiplier;
import com.example.nreed.awildencounterappears.Classes.Objects.DifficultyEnum;
import com.example.nreed.awildencounterappears.Classes.Objects.Lists.MonsterList;
import com.example.nreed.awildencounterappears.Classes.Objects.Monster;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MonsterFragment.OnListFragmentInteractionListener {
    com.shawnlin.numberpicker.NumberPicker partyLevel = null;
    com.shawnlin.numberpicker.NumberPicker numberInParty = null;
    com.shawnlin.numberpicker.NumberPicker minCR = null;
    FloatingActionButton floatingActionButton = null;

    LinearLayout additionalOptions =null;
    ImageButton showAdditionalOptionsButton =null;

    TextView partyXP = null;
    TextView monstersToKill = null;

    public static DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setMainBackground();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        additionalOptions =(LinearLayout) findViewById(R.id.additionalOptions);
        showAdditionalOptionsButton=(ImageButton) findViewById(R.id.showAdditionalOptionsButton);
        showAdditionalOptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(additionalOptions.getVisibility() == View.GONE) {
                    additionalOptions.setVisibility(View.VISIBLE);
                    showAdditionalOptionsButton.setRotation(180);
                }else {
                    additionalOptions.setVisibility(View.GONE);
                    showAdditionalOptionsButton.setRotation(0);
                }
            }
        });
        partyLevel = (com.shawnlin.numberpicker.NumberPicker)findViewById(R.id.levelOfPartyNumberPicker);
        numberInParty = (com.shawnlin.numberpicker.NumberPicker) findViewById(R.id.numberOfMembers);
        partyXP = (TextView) findViewById(R.id.partyXP);


        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateEncounter();
            }
        });


        try {
            databaseHelper = DatabaseHelper.GetInstance(getApplicationContext());
            databaseHelper.createDataBase();
            databaseHelper.openDataBase();
        }catch (Exception ex){

        }
    }

    private void setMainBackground() {
        Bitmap bitmap = getBitmap(this, R.drawable.ic_skulls);

        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
        bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        RelativeLayout constraintLayout = (RelativeLayout) findViewById(R.id.mainConstraintLayout);
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
        getMenuInflater().inflate(R.menu.main2, menu);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void SetPartyXP(double xp){
        partyXP.setText(String.valueOf(xp));
    }


    private void CreateEncounter(){
        XPCalculator calculator = new XPCalculator(DifficultyEnum.Easy);
        int partyLevelInt =partyLevel.getValue();
        int partyMembersInt = numberInParty.getValue();

        Random randomSelect = new Random();
        int randomMonsterHorde = randomSelect.nextInt(partyLevelInt+1);

        double initialXPPool = calculator.CalculatePartyXP(partyMembersInt, partyLevelInt, MonsterMultiplier.GetMonsterMultiplier(randomMonsterHorde,partyMembersInt));
        partyXP.setText(String.valueOf(initialXPPool));



        MonsterDataAdapter monsterDataAdapter = new MonsterDataAdapter(getApplicationContext());
        MonsterList monsters = monsterDataAdapter.GetMonsterList("cave", partyLevelInt, initialXPPool, randomMonsterHorde, partyMembersInt, partyLevelInt,DifficultyEnum.Easy);

        if(monsters == null) return;
        partyXP.setText("Total XP: " + String.valueOf(monsterDataAdapter.XP));

        if(!monsters.isEmpty()){
            ShowMonsterList(monsters);
        }else{
            LinearLayout linearLayout= (LinearLayout) findViewById(R.id.monsterLayout);
            linearLayout.removeAllViews();
        }
    }

    private void ShowMonsterList(MonsterList monsters) {
        LinearLayout linearLayout= (LinearLayout) findViewById(R.id.monsterLayout);

        MonsterFragment monsterFragment = new MonsterFragment();
        Bundle b = new Bundle();
        b.putParcelable("monsters", monsters);
        monsterFragment.setArguments(b);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(linearLayout.getId(),monsterFragment,"fragment1");
        fragmentTransaction.commit();
    }

    @Override
    public void onListFragmentInteraction(Monster item) {

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static Bitmap getBitmap(VectorDrawable vectorDrawable) {
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return bitmap;
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.mainhamburgermenu, menu);
//        return true;
//    }

    private static Bitmap getBitmap(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof VectorDrawable) {
            return getBitmap((VectorDrawable) drawable);
        } else {
            throw new IllegalArgumentException("unsupported drawable type");
        }
    }
}
