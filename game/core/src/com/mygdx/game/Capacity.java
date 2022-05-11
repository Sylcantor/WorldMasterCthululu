package com.mygdx.game;

public class Capacity {
    private String name;
    private String description;
    private int rechargeTime;
    private int damage;
    private int costPA;
    private int range;

    Capacity(String name, String description, int rechargeTime, int damage, int costPA, int range){
        this.name = name;
        this.description = description;
        this.rechargeTime = rechargeTime;
        this.damage = damage;
        this.costPA = costPA;
        this.range = range;
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(int rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    /*
    public Effect getEffect() {
        return effect;
    }
    */

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCostPA() {
        return costPA;
    }

    public void setCostPA(int costPA) {
        this.costPA = costPA;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

}
