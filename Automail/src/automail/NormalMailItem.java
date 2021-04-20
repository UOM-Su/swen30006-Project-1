package automail;

import simulation.Simulation;

import java.util.Map;
import java.util.TreeMap;

// import java.util.UUID;

/**
 * Represents a mail item
 */
public class NormalMailItem extends MailItem {

    // store each item's property
    private double charge;
    private double cost;
    private double fee;
    private double activity;

    /**
     * Constructor for a NormalMailItem
     * @param dest_floor the destination floor intended for this mail item
     * @param arrival_time the time that the mail arrived
     * @param weight the weight of this mail item
     */
    public NormalMailItem(int dest_floor, int arrival_time, int weight){
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
