package com.mygdx.game;

public class PointAction {

    private int tempsRechargePA;
    private Boolean isReady = true;

    public PointAction(int tempsRechargePA){
        this.tempsRechargePA=tempsRechargePA;
    }

    public void setPaStatement(Boolean state){
        this.isReady=state;
    }
    
    Boolean getPaStatement(){
        return this.isReady;
    }

    public void startRecharge(){
        setPaStatement(true);
    }

}