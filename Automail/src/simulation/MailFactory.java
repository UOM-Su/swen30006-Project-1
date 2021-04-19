package simulation;

import automail.MailItem;

import java.util.Random;

public class MailFactory {

    // record the limits the maximum weight of the mail
    public final int MAIL_MAX_WEIGHT;
    private final Random random;

    /**
     * Constructor for mail factory
     * @param maxMailWeight limits the maximum weight of the mail
     * @param random a random number
     */
    public MailFactory(int maxMailWeight, Random random) {
        this.MAIL_MAX_WEIGHT = maxMailWeight;
        this.random = random;
    }

    /**
     * @return a new mail item that needs to be delivered
     */
    public MailItem generateMail(){
        MailItem newMailItem;
        int destinationFloor = generateDestinationFloor();
        int arrivalTime = generateArrivalTime();
        int weight = generateWeight();

        newMailItem = new MailItem(destinationFloor,arrivalTime,weight);
        return newMailItem;
    }

    /**
     * @return a destination floor between the ranges of GROUND_FLOOR to FLOOR
     */
    private int generateDestinationFloor(){
        return Building.LOWEST_FLOOR + random.nextInt(Building.FLOORS);
    }

    /**
     * @return a random weight
     */
    private int generateWeight(){
        final double mean = 200.0; // grams for normal item
        final double stddev = 1000.0; // grams
        double base = random.nextGaussian();
        if (base < 0) base = -base;
        int weight = (int) (mean + base * stddev);
        return weight > MAIL_MAX_WEIGHT ? MAIL_MAX_WEIGHT : weight;
    }

    /**
     * @return a random arrival time before the last delivery time
     */
    private int generateArrivalTime(){
        return 1 + random.nextInt(Clock.MAIL_RECEVING_LENGTH);
    }



}
