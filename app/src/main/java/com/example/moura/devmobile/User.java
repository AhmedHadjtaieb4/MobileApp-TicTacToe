package com.example.moura.devmobile;

public class User {
    private String email ;
    private String name ;
    private String address ;
    private String lastName ;
    private String tel;
    private String function ;
    private String password ;
    public User(String em ,String na,String Addr,String lsn,String tel ,String funct,String pass)
    {

        this.address=Addr;
        this.email=em ;
        this.function=funct;
        this.password=pass;
        this.tel=tel;
        this.lastName=lsn;
        this.name=na;

    }

public String getEmail()
{

    return this.email;
}
    public String getName()
    {

        return this.name;
    }
    public String getPassword()
    {

        return this.password;
    }
    public String getAddress()
    {

        return this.address;
    }
    public String getTel()
    {

        return this.tel;
    }
    public String getLastName()
    {

        return this.lastName;
    }
    public String getFunction()
    {

        return this.function;
    }



}
