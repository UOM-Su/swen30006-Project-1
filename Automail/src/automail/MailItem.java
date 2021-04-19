package automail;

import simulation.Simulation;

import java.util.Map;
import java.util.TreeMap;

// import java.util.UUID;

/**
 * Represents a mail item
 */
public class MailItem {
	
    /** Represents the destination floor to which the mail is intended to go */
    protected final int destination_floor;
    /** The mail identifier */
    protected final String id;
    /** The time the mail item arrived */
    protected final int arrival_time;
    /** The weight in grams of the mail item */
    protected final int weight;

    // store each item's property
    private double charge;
    private double cost;
    private double fee;
    private double activity;
    // record the estimate charge for the current item
    protected double estimateChargeAtFirst;

    /**
     * Constructor for a MailItem
     * @param dest_floor the destination floor intended for this mail item
     * @param arrival_time the time that the mail arrived
     * @param weight the weight of this mail item
     */
    public MailItem(int dest_floor, int arrival_time, int weight){
        this.destination_floor = dest_floor;
        this.id = String.valueOf(hashCode());
        this.arrival_time = arrival_time;
        this.weight = weight;
        this.estimateChargeAtFirst = 0;
        this.charge = 0;
        this.cost = 0;
        this.fee = 0;
        this.activity = 0;
    }

    /**
     * It will present the action of a robot
     * @return the string of the action
     */
    @Override
    public String toString(){
        return String.format("Mail Item:: ID: %6s | Arrival: %4d | Destination: %2d | Weight: %4d", id, arrival_time, destination_floor, weight);
    }

    /**
     * It will print the action and charge after delivering an item
     * @return the action and charge after delivering an item
     */
    public String displayCharge() {
        return String.format("Mail Item:: ID: %6s | Arrival: %4d | Destination: %2d | Weight: %4d | Charge: %4.2f | Cost: %4.2f | Fee: %4.2f | Activity: %4.2f", id, arrival_time, destination_floor, weight, charge, cost, fee, activity);
    }

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
   
	static private int count = 0;
	static private Map<Integer, Integer> hashMap = new TreeMap<Integer, Integer>();

	@Override
	public int hashCode() {
		Integer hash0 = super.hashCode();
		Integer hash = hashMap.get(hash0);
		if (hash == null) { hash = count++; hashMap.put(hash0, hash); }
		return hash;
	}

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getActivity() {
        return activity;
    }

    public void setActivity(double activity) {
        this.activity = activity;
    }
}
