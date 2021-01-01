package com.example.moura.devmobile;


public class Produit {
private String name;
private String prix;
private String  id;
//private String quantity;
private String color ;
private String model;
public Produit(String namep,String prix,String colorp,String idp,String model) {

    this.name = namep;
    this.prix = prix;
    this.color = colorp;
    this.id = idp;
    //this.quantity = quantity;
    this.model=model;
}

public String getName()
{
    return  this.name;


}


    public String getColor()
    {
        return  this.color;

    }


    public String getPrix()
    {
        return  this.prix;


    }


    public String getid()
    {
        return  this.id;


    }

    public String getModel()
    {
        return  this.model;


    }


}
