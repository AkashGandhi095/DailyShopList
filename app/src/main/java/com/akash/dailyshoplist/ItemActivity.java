package com.akash.dailyshoplist;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.akash.dailyshoplist.Adapters.ItemAdapter;
import com.akash.dailyshoplist.Adapters.TittleAdapter;
import com.akash.dailyshoplist.Database.DataBaseHandler;
import com.akash.dailyshoplist.Model.ItemListModel;
import com.akash.dailyshoplist.Model.TittleListModel;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView tittle_text;
    private EditText  item_text;
    private Button Cancel , Add;
    private DataBaseHandler handler;
    private LayoutInflater inflater;
    private ArrayList<ItemListModel> ItemList;
    private View view;
    public static final String EXTRA_TITTLE = "com.DailyShopList.application";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Toolbar toolbar = findViewById(R.id.toolBar);
        tittle_text = findViewById(R.id.Item_tittle);
        handler = new DataBaseHandler(ItemActivity.this);
        dialogBuilder = new AlertDialog.Builder(this);
        inflater = this.getLayoutInflater();
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Log.d("Intent_data " , "" +getIntent().getIntExtra(TittleAdapter.ID_TITTLE , 0) );
        ItemList = handler.getItems(getIntent().getIntExtra(TittleAdapter.ID_TITTLE , 0));
        tittle_text.setText(getIntent().getStringExtra(TittleAdapter.NAME_TITTLE));
        if(ItemList.isEmpty() && tittle_text.getText().toString().isEmpty())
        {
            Log.d("ItemListOnCreate" , "First Time user Create itemList");
            ItemList = new ArrayList<>();
            AddTittlePopUp();
        }








        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(ItemList.size());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemAdapter(ItemList , ItemActivity.this);
        recyclerView.setAdapter(adapter);



    }

    private void AddTittlePopUp() {
        view = inflater.inflate(R.layout.edit_tittle_view , null , false);
        dialogBuilder.setTitle("Item Tittle");
        Button save_button = view.findViewById(R.id.Save_button);
        Button cancel = view.findViewById(R.id.Cancel_tittle);
        final EditText tittle_editor = view.findViewById(R.id.add_tittle);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SaveTittle(tittle_editor);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
        dialogBuilder.setView(view);
        dialogBuilder.setCancelable(false);
        dialog = dialogBuilder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
            {


                finish();
                break;
            }
            case R.id.add:
            {
                AddItemDialogPopUp();
                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    private void SaveTittle(EditText tittle_edit_text) {
        String tittle = tittle_edit_text.getText().toString().trim();
        handler.AddTittle(new ItemListModel(tittle));
        tittle_text.setText(tittle);
        dialog.dismiss();
    }

    private void AddItemDialogPopUp() {
        dialogBuilder.setTitle("Item Names");
        view = inflater.inflate(R.layout.edit_items_view , null , false);
        item_text = view.findViewById(R.id.enter_item);
        Add = view.findViewById(R.id.Add_button);
        Cancel = view.findViewById(R.id.Cancel_button);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveItemList();

                dialog.dismiss();

            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialogBuilder.setView(view);

        dialog = dialogBuilder.create();
        dialog.show();


    }

    private void SaveItemList() {
        int tittle_id = handler.getTittleId(tittle_text.getText().toString());
        ItemListModel data = new ItemListModel(tittle_id , item_text.getText().toString() , false);
        handler.AddItem(data);

        ItemList.add(data);
        adapter.notifyItemInserted(ItemList.size());

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.close();
    }
}
