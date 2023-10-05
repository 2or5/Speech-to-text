package com.speechtotext.exeptions;

public class WrongIdException extends RuntimeException{
    public WrongIdException(String messages){
        super(messages);
    }
}
