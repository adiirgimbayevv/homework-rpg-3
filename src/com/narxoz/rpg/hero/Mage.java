package com.narxoz.rpg.hero;

public class Mage implements Hero {
    private final String name;
    private final int power;
    private int health;

    public Mage(String name) {
        this.name=name;
        this.power=35;
        this.health=70;}
    @Override
    public String getName() { return name; }
    @Override
    public int getPower() { return power; }
    @Override
    public void receiveDamage(int amount) {
        this.health=Math.max(0,this.health-amount);
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }
}