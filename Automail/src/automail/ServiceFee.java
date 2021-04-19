package automail;

import simulation.FeeFinder;

import java.util.HashMap;

public class ServiceFee {

    private final FeeFinder feeFinder = new FeeFinder();

    // It will record the service fee corresponds to each floor to handle the failure of looking up fee
    private HashMap<Integer, Double> checkedFee = new HashMap<Integer, Double>();

    private int numOfLookups;
    private int successLookups;
    private int failLookups;

    /**
     * Constructor of a serviceFee
     */
    public ServiceFee() {
        numOfLookups = 0;
        successLookups = 0;
        failLookups = 0;
    }

    /**
     * It will look up the service fee using modem
     * @param current_floor the floor that a robot currently at
     * @return the service fee that the tenant need to pay
     */
    public double findServiceFee(int current_floor) {
        numOfLookups += 1;
        double serviceFee = feeFinder.findFee(current_floor);
        // It will check whether successfully check service fee through the modem
        if (serviceFee < 0) {
            // fail to look up the service fee
            failLookups += 1;
            if (checkedFee.containsKey(current_floor)) {
                serviceFee = checkedFee.get(current_floor);
            } else {
                serviceFee = 0;
            }
        } else {
            // successfully look up the service fee
            successLookups += 1;
            checkedFee.put(current_floor, serviceFee);
        }
        return serviceFee;
    }

    /**
     * get the number of lookups
     * @return the number of lookups
     */
    public int getNumOfLookups() {
        return numOfLookups;
    }

    /**
     * get the number of successful lookups
     * @return the number of successful lookups
     */
    public int getSuccessLookups() {
        return successLookups;
    }

    /**
     * get the number failed lookups
     * @return the number failed lookups
     */
    public int getFailLookups() {
        return failLookups;
    }


}
