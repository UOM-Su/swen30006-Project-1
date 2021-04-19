package automail;

public class ActivityCost {

    private double activity;

    /**
     * Constructor of an activity cost
     */
    public ActivityCost() {
        this.activity = 0;
    }
    /**
     * It will increase the activity depends on the movement of robots
     * @param activity number of activities that needed to add
     */
    public void addActivityUnit(double activity) {
        this.activity += activity;
    }

    /**
     * get the activity cost
     * @return the activity cost
     */
    public double getActivity() {
        return activity;
    }

    /**
     * reset the activity cost to 0 after delivering an item
     */
    public void resetActivity() {
        this.activity = 0;
    }

}
