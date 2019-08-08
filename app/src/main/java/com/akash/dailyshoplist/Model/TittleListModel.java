package com.akash.dailyshoplist.Model;

public class TittleListModel {
    private int Tittle_Id;
    private String Tittle_name;

    public TittleListModel() {
    }

    public TittleListModel(int Tittle_id)
    {
        this.Tittle_Id = Tittle_id;
    }

    public TittleListModel(String Tittle_name) {
        this.Tittle_name = Tittle_name;
    }

    public TittleListModel(int Tittle_Id , String Tittle_name) {
        this.Tittle_Id = Tittle_Id;
        this.Tittle_name = Tittle_name;
    }

    public int getTittle_Id() {
        return Tittle_Id;
    }

    public void setTittle_Id(int tittle_Id) {
        Tittle_Id = tittle_Id;
    }

    public String getTittle_name() {
        return Tittle_name;
    }

    public void setTittle_name(String tittle_name) {
        Tittle_name = tittle_name;
    }
}
