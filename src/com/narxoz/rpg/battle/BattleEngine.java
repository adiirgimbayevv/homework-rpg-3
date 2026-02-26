package com.narxoz.rpg.battle;

import java.util.List;
import java.util.Random;

public final class BattleEngine {

    private static BattleEngine instance;
    private Random random=new Random();
    private BattleEngine() {}

    public static BattleEngine getInstance() {
        if (instance==null) {
            instance=new BattleEngine();}
        return instance;
    }

    public BattleEngine setRandomSeed(long seed) {
        this.random=new Random(seed);
        return this;
    }
    public EncounterResult runEncounter(List<Combatant> teamA, List<Combatant> teamB) {
        EncounterResult result=new EncounterResult();
        int round=0;
        result.addLog("---BATTLE START---");

        while (isAnyAlive(teamA)&&isAnyAlive(teamB)) {
            round++;
            result.addLog("\n[ROUND "+round+"]");
            processAttackTurn(teamA, teamB, result);
            if (!isAnyAlive(teamB)) break;
            processAttackTurn(teamB,teamA,result);}

        result.setRounds(round);
        result.setWinner(isAnyAlive(teamA)?"Team A (Heroes)":"Team B (Enemies)");
        result.addLog("\nVICTORY: "+result.getWinner()+" won the battle!");

        return result;
    }

    private void processAttackTurn(List<Combatant> attackers, List<Combatant> defenders, EncounterResult result) {
        for (Combatant attacker:attackers){
            if (attacker.isAlive()){
                Combatant target=getRandomTarget(defenders);
                if (target!=null) {
                    int dmg=attacker.getAttackPower();
                    target.takeDamage(dmg);
                    result.addLog(attacker.getName()+" attacks "+target.getName()+" for "+dmg+" damage.");
                }}}
    }

    private boolean isAnyAlive(List<Combatant> team) {
        return team.stream().anyMatch(Combatant::isAlive);
    }

    private Combatant getRandomTarget(List<Combatant> team) {
        List<Combatant> alive=team.stream().filter(Combatant::isAlive).toList();
        if (alive.isEmpty()) return null;
        return alive.get(random.nextInt(alive.size()));}
}