package com.example.bai4_tuan6;

public class Product_listView extends Commons{
    private Catalog_spin Dmuc;
    public Catalog_spin getDmuc(){
        return Dmuc;
    }
    public void setDmuc(Catalog_spin dmuc){
        Dmuc = dmuc;
    }
    public Product_listView(String ma, String name, Catalog_spin dmuc){
        super(ma, name);
        Dmuc = dmuc;

    }
    public Product_listView(String ma, String name){
        super(ma, name);
    }
    public Product_listView(){
        super();
    }
}

