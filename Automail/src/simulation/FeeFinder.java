package simulation;

// This class is used to avoid low cohesion (GRASP)
public class FeeFinder {

    /**
     * constructor of a fee finder which connect the Wifi modem
     */
    public FeeFinder() {

    }

    /**
     *  It will find the service fee of the corresponding floor
     * @param floor the floor a robot currently at
     * @return the look up fee of the current floor
     */
    public double findFee(int floor) {
        return Simulation.getwModem().forwardCallToAPI_LookupPrice(floor);
    }

}
