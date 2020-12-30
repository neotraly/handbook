package com.example.Helper;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    private ListView listView;
    private String[] arrayComponent;
    private ArrayAdapter<String> adapter;
    private Toolbar toolbar;
    public int category;



    private void funcCategory(int title, int arrayList, int index)
    {
        category = index;
        toolbar.setTitle(title);
        arrayComponent = getResources().getStringArray(arrayList);
        adapter.clear();
        adapter.addAll(arrayComponent);
        adapter.notifyDataSetInvalidated();
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        //Добавление кнопки для выпадающего меню
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        //Работа с массивом данных и вывод
        listView = findViewById(R.id.list_view);
        arrayComponent = getResources().getStringArray(R.array.process);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<String>(Arrays.asList(arrayComponent)));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                //Переход из Main в Content, где находятся наши сообщения
                //Порядок!!!
                Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                intent.putExtra("category", category);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        setTitle(R.string.menu_process);
        return true;
    }

    //Отслеживание кнопок выпадающего меню
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id  = item.getItemId();
        if (id == R.id.id_process)
        {
            funcCategory(R.string.menu_process, R.array.process, 0);
        }
        else if (id == R.id.id_graphic)
        {
            funcCategory(R.string.menu_graphic, R.array.graphic_cards, 1);
        }
        else if (id == R.id.id_operative)
        {
            funcCategory(R.string.menu_operative, R.array.operative_memory, 2);
        }
        else if (id == R.id.id_memory)
        {
            funcCategory(R.string.menu_memory, R.array.slow_memory, 3);
        }

        //После нажатия на любую из кнопок меню закрывается
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}