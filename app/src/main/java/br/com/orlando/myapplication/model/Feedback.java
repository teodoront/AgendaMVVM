package br.com.orlando.myapplication.model;

public class Feedback {

    private boolean mSuccess = true;
    private String mMessage = "";

    public Feedback(String message){
        this.mMessage = message;
    }

    public Feedback(String message, boolean success){
        this.mMessage = message;
        this.mSuccess = success;
    }

    public boolean isSuccess(){
        return this.mSuccess;
    }

    public String getMessage(){
        return this.mMessage;
    }

}
