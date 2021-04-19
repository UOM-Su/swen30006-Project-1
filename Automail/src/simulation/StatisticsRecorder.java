package simulation;

import automail.Robot;
import automail.Charger;

public class StatisticsRecorder {

    private int numOfLookups;
    private int successLookups;
    private int failLookups;
    private int itemDelivered;
    private double numActivities;
    private double totalActivityCost;
    private double totalServiceFee;

    /**
     * It will calculate the statistics for the whole simulation
     * @param robots an array of all robots in this building
     * @param chargerInMailPool the charger in the mail pool
     */
    public void getStatsFromRobot(Robot[] robots, Charger chargerInMailPool) {
        // get the statistic of delivered item
        for (int i = 0; i < robots.length; i++) {
            // access charger in each robot and get the statistics
            Charger currentCharge = robots[i].getCharger();
            itemDelivered += currentCharge.getItemDelivered();
            numActivities += currentCharge.getTotalActivity();
            totalActivityCost += currentCharge.getTotalActivityCost();
            totalServiceFee += currentCharge.getTotalServiceFee();
            numOfLookups += currentCharge.getServiceFee().getNumOfLookups();
            successLookups += currentCharge.getServiceFee().getSuccessLookups();
            failLookups += currentCharge.getServiceFee().getFailLookups();
        }
        // get the look up of items before delivering
        numOfLookups += chargerInMailPool.getServiceFee().getNumOfLookups();
        successLookups += chargerInMailPool.getServiceFee().getSuccessLookups();
        failLookups += chargerInMailPool.getServiceFee().getFailLookups();
    }

    /**
     * It will present the statistics
     * @return the statistics
     */
    public String toString() {
        return String.format("Total item delivered: %d\nTotal billable activities: %.2f\nTotal activity cost: %.2f\nTotal service cost: %.2f\nTotal number of lookups: %d\nTotal number of successful lookups: %d\nTotal number of failure lookups:%d\n", itemDelivered, numActivities, totalActivityCost, totalServiceFee, numOfLookups, successLookups, failLookups);
    }

}
