package com.akash.dailyshoplist;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.akash.dailyshoplist.Adapters.TittleAdapter;
import com.akash.dailyshoplist.Database.DataBaseHandler;
import com.akash.dailyshoplist.Model.TittleListModel;
import com.akash.dailyshoplist.Utils.DataUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TittleAdapter adapter;
    private ArrayList<TittleListModel> tittleListModels;
    private DataBaseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView_tittle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        handler = new DataBaseHandler(this);
        tittleListModels = handler.getAllTittles();
        if(tittleListModels == null)
        {
            Log.d("onCreate" , "tittleList is null");
        }
        adapter = new TittleAdapter(tittleListModels , MainActivity.this);
        recyclerView.setAdapter(adapter);


    }

    public void OpenView(View view) {
        startActivity(new Intent(MainActivity.this , ItemActivity.class
        ));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        tittleListModels.clear();
        Log.d("Size" , "tittleList Size before init : " +tittleListModels.size());
        tittleListModels = handler.getAllTittles();
        adapter.SetTittles(tittleListModels);
        Log.d("Size" , "tittleList : " + tittleListModels.size());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.close();
    }
}
