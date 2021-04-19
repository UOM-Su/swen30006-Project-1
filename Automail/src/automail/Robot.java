package automail;

import exceptions.ExcessiveDeliveryException;
import exceptions.ItemTooHeavyException;
import simulation.IMailDelivery;

public abstract class Robot {
    IMailDelivery delivery;
    protected String id;

    /** Possible states the robot can be in */
    public enum RobotState { DELIVERING, WAITING, RETURNING }
    public Robot.RobotState current_state;
    protected int current_floor;
    protected int destination_floor;
    protected MailPool mailPool;
    protected boolean receivedDispatch;

    protected int deliveryCounter;

    protected Charger charger = new Charger();

    /**
     * This is called when a robot is assigned the mail items and ready to dispatch for the delivery
     */
    public void dispatch() {
            receivedDispatch = true;
    }

    public abstract void operate() throws ExcessiveDeliveryException;

    public abstract void setDestination();

    /**
     * Generic function that moves the robot towards the destination
     * @param destination the floor towards which the robot is moving
     */
    public void moveTowards(int destination) {
        if(current_floor < destination){
            current_floor++;
        } else {
            current_floor--;
        }
    }

    public abstract void changeState(RobotState nextState);

    public abstract void addToHand(MailItem mailItem) throws ItemTooHeavyException;
    public abstract void addToTube(MailItem mailItem) throws ItemTooHeavyException;

    public abstract boolean isEmpty();

    public abstract boolean spaceForItem();

    public abstract void load(MailItem mailItem);

    public Charger getCharger() {
        return charger;
    }

}
