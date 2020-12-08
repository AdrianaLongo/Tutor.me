package com.example.progetto_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.progetto_android.conndata.Dao;
import com.example.progetto_android.globals.ShareDataViewModel;


public class MainActivity extends AppCompatActivity {

    private ShareDataViewModel sd;
    private Menu menu;
    private Dao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sd = ShareDataViewModel.getInstance();
        dao = Dao.getInstance(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //gestione del menu che fa il cazzo che vuole, bisogna capire come e dove impostarlo
        /*sd.getLogin().observeForever(data -> {
            if (menu == null) return;
            if (data.getLoggedIn()) {
                MenuItem item = menu.findItem(R.id.action_login);
                item.setVisible(false);
                item = menu.findItem(R.id.action_logout);
                item.setVisible(true);
                item = menu.findItem(R.id.action_prenotazioni);
                item.setVisible(true);
            } else {
                MenuItem item = menu.findItem(R.id.action_login);
                item.setVisible(true);
                item = menu.findItem(R.id.action_logout);
                item.setVisible(false);
                item = menu.findItem(R.id.action_prenotazioni);
                item.setVisible(false);
            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }
}