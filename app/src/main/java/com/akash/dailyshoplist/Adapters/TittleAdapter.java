package com.akash.dailyshoplist.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.akash.dailyshoplist.Database.DataBaseHandler;
import com.akash.dailyshoplist.ItemActivity;
import com.akash.dailyshoplist.Model.TittleListModel;
import com.akash.dailyshoplist.R;

import java.util.ArrayList;

public class TittleAdapter extends RecyclerView.Adapter<TittleAdapter.ViewHolder> {

    public static final String ID_TITTLE = "com.tittle_id.DailyShopList";
    public static final String NAME_TITTLE = "com.tittle_name.DailyShopList";
    private ArrayList<TittleListModel> tittleList;
    private Context context;
    private LayoutInflater inflater;


    public TittleAdapter(ArrayList<TittleListModel> tittleList , Context context)
    {
        this.tittleList = tittleList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.tittle_row_view , null , false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TittleListModel listModel = tittleList.get(i);
        viewHolder.tittle_view.setText(listModel.getTittle_name());
    }

    public void SetTittles(ArrayList<TittleListModel> tittleList)
    {
        this.tittleList = tittleList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(tittleList != null)
            return tittleList.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tittle_view;
        public ImageButton delete_button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tittle_view = itemView.findViewById(R.id.tittle_view);
            delete_button = itemView.findViewById(R.id.Delete_button);
            delete_button.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {


            if (v.getId() == R.id.Delete_button) {
                DeleteItems();
            }
            else {
                OpenItemActivity();
            }
        }



        private void DeleteItems() {

            DataBaseHandler handler = new DataBaseHandler(context);
            handler.DeleteData(tittleList.get(getAdapterPosition()).getTittle_Id());
            tittleList.remove(getAdapterPosition());
            notifyItemRemoved(getAdapterPosition());

        }

        private void OpenItemActivity() {
            int tittleId = tittleList.get(getAdapterPosition()).getTittle_Id();
            String tittle_name = tittleList.get(getAdapterPosition()).getTittle_name();
            if (TextUtils.isEmpty(tittle_name)) {
                tittle_name = "";
            }
            Intent intent = new Intent(context, ItemActivity.class);
            intent.putExtra(ID_TITTLE, tittleId);
            intent.putExtra(NAME_TITTLE, tittle_name);
            context.startActivity(intent);
        }

    }


}
