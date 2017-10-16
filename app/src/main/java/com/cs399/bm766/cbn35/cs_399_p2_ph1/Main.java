package com.cs399.bm766.cbn35.cs_399_p2_ph1;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String level1[] = {"dictionary", "indicatory"};
    String level2[] = {"saltier", "realist", "retails"};
    String level3[] = {"rescued", "reduced", "secured", "seducer"};

    private void setupLevel(final String[] levelText) {
        final TextView anagramText = (TextView) findViewById(R.id.anagramText);
        final TextView answersText = (TextView) findViewById(R.id.answersText);
        final EditText textEdit = (EditText) findViewById(R.id.textInput);
        final int[] answers = {0};

        anagramText.setText(levelText[0]);
        answersText.setText("");

        textEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                return;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                return;
            }

            @Override
            public void afterTextChanged(Editable s) {
                for(int i = 1; i < levelText.length; i++) {
                    if(s.toString().equals(levelText[i])) {
                        if(answersText.getText().length() == 0) {
                            answersText.setText(levelText[i]);
                            answers[0]++;
                        } else {
                            answersText.setText(answersText.getText() + ", " + levelText[i]);
                            answers[0]++;
                        }
                        if(answers[0] == levelText.length - 1) {
                            anagramText.setText("Congratulations!");
                            answersText.setText("Youve found all the anagrams for this level!");
                        }
                    }
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView placeholderText = (TextView) findViewById(R.id.anagramText);
        placeholderText.setText("Welcome to the Anagram game! Swipe right for a level menu.");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        TextView placeholderText = (TextView) findViewById(R.id.anagramText);

        if (id == R.id.nav_level1) {
            setupLevel(level1);
        } else if (id == R.id.nav_level2) {
            setupLevel(level2);
        } else if (id == R.id.nav_level3) {
            setupLevel(level3);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
