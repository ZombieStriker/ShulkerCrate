package me.zombie_striker.shulkercrate.crate;

public class RewardCommand implements Reward{

    private String command;
    private double chance;

    public RewardCommand(String command, double chance){
        this.chance = chance;
        this.command = command;
    }

    @Override
    public double getChanceOfReward() {
        return chance;
    }

    public String getCommand() {
        return command;
    }
}
