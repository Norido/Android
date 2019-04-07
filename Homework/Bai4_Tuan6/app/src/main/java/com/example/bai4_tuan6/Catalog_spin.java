package com.example.bai4_tuan6;

import java.util.ArrayList;

public class Catalog_spin extends Commons {
    private ArrayList<Product_listView> listSP = null;
    public Catalog_spin(String ma, String name){
        super(ma,name);
        this.listSP = new ArrayList<Product_listView>();
    }
    public Catalog_spin(){
        super();
        this.listSP = new ArrayList<>();
    }
    public boolean isDuplicate(Product_listView p)
    {
        for(Product_listView p1: listSP)
        {
            if (p1.getId().trim().equalsIgnoreCase(p.getId().trim()))
                return true;
        }
        return false;
    }
    public boolean addProduct(Product_listView p){
        boolean isDup = isDuplicate(p);
        if (!isDup){
            p.setDmuc(this);
            return listSP.add(p);
        }
        return !isDup;
    }
    public ArrayList<Product_listView>getListSP(){
        return this.listSP;
    }
    public int size(){
        return listSP.size();
    }
    public Product_listView get(int i){
        return listSP.get(i);
    }
}
