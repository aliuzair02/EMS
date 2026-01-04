package org.template.common.models;

public class BaseVO extends BaseObject{
    public BaseVO(){

    }

    public BaseVO(boolean status, String message){
        this.setStatus(status);
        this.setMessage(message);
    }
}
