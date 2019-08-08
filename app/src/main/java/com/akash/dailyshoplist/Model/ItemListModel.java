package com.akash.dailyshoplist.Model;

public class ItemListModel extends TittleListModel{


    private String Item_name;
    private boolean is_Item_Checked;


    public ItemListModel(String Tittle_name) {
        super(Tittle_name);
    }


    public ItemListModel( String Item_name ,  boolean is_Item_Checked)
    {
        this.Item_name = Item_name;
        this.is_Item_Checked = is_Item_Checked;

    }
    public ItemListModel(int tittle_id , String Item_name , boolean is_Item_Checked)
    {
        super(tittle_id);
        this.Item_name = Item_name;
        this.is_Item_Checked = is_Item_Checked;
    }


    @Override
    public void setTittle_name(String tittle_name) {
        super.setTittle_name(tittle_name);
    }

    @Override
    public String getTittle_name() {
        return super.getTittle_name();
    }



    @Override
    public int getTittle_Id() {
        return super.getTittle_Id();
    }


    public String getItem_name() {
        return Item_name;
    }

    public void setItem_name(String item_name) {
        Item_name = item_name;
    }

    public boolean isIs_Item_Checked() {
        return is_Item_Checked;
    }

    public void setIs_Item_Checked(boolean is_Item_Checked) {
        this.is_Item_Checked = is_Item_Checked;
    }
}
