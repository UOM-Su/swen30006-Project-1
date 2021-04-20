package automail;

public abstract class MailItem {
    /** Represents the destination floor to which the mail is intended to go */
    protected int destination_floor;
    /** The mail identifier */
    protected String id;
    /** The time the mail item arrived */
    protected int arrival_time;
    /** The weight in grams of the mail item */
    protected int weight;

    // record the estimate charge for the current item
    protected double estimateChargeAtFirst;

    /**
     * It will present the action of a robot
     * @return the string of the action
     */
    @Override
    public abstract String toString();

    /**
     * It will print the action and charge after delivering an item
     * @return the action and charge after delivering an item
     */
    public abstract String displayCharge();

    /**
     * set the value of estimate charge before delivery
     * @param estimateChargeAtFirst the estimate charge before delivery
     */
    public void setEstimateChargeAtFirst(double estimateChargeAtFirst) {
        this.estimateChargeAtFirst = estimateChargeAtFirst;
    }

    /**
     * get the destination floor of the item
     * @return the destination floor of the mail item
     */
    public int getDestFloor() {
        return destination_floor;
    }

    /**
     *
     * @return the ID of the mail item
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return the arrival time of the mail item
     */
    public int getArrivalTime(){
        return arrival_time;
    }

    /**
     *
     * @return the weight of the mail item
     */
    public int getWeight(){
        return weight;
    }

}
