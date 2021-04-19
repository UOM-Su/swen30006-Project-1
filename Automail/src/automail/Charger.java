package automail;

import simulation.FeeFinder;

import java.util.HashMap;

public class Charger {
    // record the unit of activities in lookup and movement
    public static final double REMOTE_LOOKUP_UNIT = 0.1;
    public static final int ROBOT_MOVEMENT_UNIT = 5;

    // record the price of each unit and these are configurable variables
    public static double ACTIVITY_UNIT_PRICE = 0.224;
    // record the percentage of MARKUP
    public static double MARKUP_PERCENTAGE = 0.059;

    // variables record the statistic of the whole system
    private int itemDelivered;
    private double totalActivityCost;
    private double totalServiceFee;
    private double totalActivity;

    // variables in single delivery
    private double charge;
    private double cost;
    private double fee;

    private ServiceFee serviceFee = new ServiceFee();
    private ActivityCost activityCost = new ActivityCost();

    /**
     * Constructor for a charger
     */
    public Charger() {
        itemDelivered = 0;
        totalActivityCost = 0;
        totalServiceFee = 0;
        totalActivity = 0;
        charge = 0;
        cost = 0;
        fee = 0;
    }

    /**
     * It will add the fee to the total fee in order to record statistics
     * @param serviceFee the service fee robot found
     */
    public void addFee(double serviceFee) {
        fee += serviceFee;
        activityCost.addActivityUnit(REMOTE_LOOKUP_UNIT);
    }

    /**
     * It will estimate the charge of an item before delivery
     * @param mailItem an item which is about to be delivered
     * @return the estimate charge for this item in order to sort
     */
    public double estimateChargeBeforeDelivery(MailItem mailItem) {
        double estimateCharge = 0;
        double activityUnits = ((mailItem.getDestFloor()-1) * ROBOT_MOVEMENT_UNIT) + REMOTE_LOOKUP_UNIT;

        double serviceMoney = serviceFee.findServiceFee(mailItem.getDestFloor());
        double activityFee = activityUnits * ACTIVITY_UNIT_PRICE;
        estimateCharge = (serviceMoney + activityFee) * (1 + MARKUP_PERCENTAGE);
        mailItem.setEstimateChargeAtFirst(estimateCharge);
        return estimateCharge;
    }

    /**
     * It will calculate the money that the tenant needs to pay when the robot finished delivery
     * @param mailItem the item be delivered
     * @return the charge of the money the tenant needs to pay
     */
    public double getFinalCharge(MailItem mailItem) {
        cost = fee + activityCost.getActivity() * ACTIVITY_UNIT_PRICE;
        charge = cost * (1 + MARKUP_PERCENTAGE);
        mailItem.setFee(fee);
        mailItem.setCharge(charge);
        mailItem.setCost(cost);
        mailItem.setActivity(activityCost.getActivity());

        return charge;
    }

    /**
     * It will record and reset the attributes of each delivery after successfully delivered
     */
    public void movementAfterCharge() {
        itemDelivered += 1;
        totalServiceFee += fee;
        totalActivityCost += cost;
        totalActivity += activityCost.getActivity();

        this.charge = 0;
        this.fee = 0;
        this.cost = 0;
        activityCost.resetActivity();
    }

    /**
     * get the total number of item that delivered
     * @return the total number of item that delivered
     */
    public int getItemDelivered() {
        return itemDelivered;
    }

    /**
     * get the total cost of activities
     * @return get the total cost of activities
     */
    public double getTotalActivityCost() {
        return totalActivityCost;
    }

    /**
     * get the total fee of the service
     * @return
     */
    public double getTotalServiceFee() {
        return totalServiceFee;
    }

    /**
     * get the total number of activities
     * @return the total number of activities
     */
    public double getTotalActivity() {
        return totalActivity;
    }

    /**
     * get the service fee
     * @return the service fee
     */
    public ServiceFee getServiceFee() {
        return serviceFee;
    }

    /**
     * get the activity cost
     * @return the activity cost
     */
    public ActivityCost getActivityCost() {
        return activityCost;
    }

}
