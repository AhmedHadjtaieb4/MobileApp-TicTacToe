package com.example.moura.devmobile;

public class Message {
    private String message ;
    private String from;
    public Message(String msg,String from)
    {
        this.message=msg ;
        this.from=from;

    }
public    String getMessage ()
    {

        return this.message;
    }
  public   String getFrom ()
    {
        return this.from;
    }

}
