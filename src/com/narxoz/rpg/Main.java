package com.narxoz.rpg;

import com.narxoz.rpg.adapter.*;
import com.narxoz.rpg.battle.*;
import com.narxoz.rpg.enemy.*;
import com.narxoz.rpg.hero.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("===RPG BATTLE ENGINE DEMO===\n");

        Warrior warrior=new Warrior("Arthas the Paladin");
        Mage mage=new Mage("Jaina the Archmage");
        Goblin scout=new Goblin();
        Goblin brute=new Goblin();

        List<Combatant> heroes=new ArrayList<>();
        heroes.add(new HeroCombatantAdapter(warrior));
        heroes.add(new HeroCombatantAdapter(mage));

        List<Combatant> enemies=new ArrayList<>();
        enemies.add(new EnemyCombatantAdapter(scout));
        enemies.add(new EnemyCombatantAdapter(brute));

        BattleEngine engine=BattleEngine.getInstance();
        engine.setRandomSeed(111L);

        EncounterResult result=engine.runEncounter(heroes, enemies);

        System.out.println("BATTLE LOGS:");
        for (String logLine:result.getBattleLog()) {
            System.out.println(logLine);
        }

        System.out.println("\n---FINAL SUMMARY---");
        System.out.println("Winner: "+result.getWinner());
        System.out.println("Duration: "+result.getRounds()+" rounds");
        System.out.println("=== THE END ===");
    }
}