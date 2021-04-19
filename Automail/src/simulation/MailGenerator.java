package simulation;

import java.util.*;

import automail.MailItem;
import automail.MailPool;

/**
 * This class generates the mail
 */
public class MailGenerator {

    public final int MAIL_MAX_WEIGHT;
    public final int MAIL_TO_CREATE;
    
    private int mailCreated;

    private final Random random;
    /** This seed is used to make the behaviour deterministic */
    
    private boolean complete;
    private MailPool mailPool;

    private Map<Integer,ArrayList<MailItem>> allMail;
    private MailFactory mailFactory;

    /**
     * Constructor for mail generation
     * @param mailToCreate roughly how many mail items to create
     * @param mailMaxWeight limits the maximum weight of the mail
     * @param mailPool where mail items go on arrival
     * @param seed random seed for generating mail
     */
    public MailGenerator(int mailToCreate, int mailMaxWeight, MailPool mailPool, HashMap<Boolean,Integer> seed){
        if(seed.containsKey(true)){
        	this.random = new Random((long) seed.get(true));
        }
        else{
        	this.random = new Random();	
        }
        // Vary arriving mail by +/-20%
        MAIL_TO_CREATE = mailToCreate*4/5 + random.nextInt(mailToCreate*2/5);
        MAIL_MAX_WEIGHT = mailMaxWeight;
        mailFactory = new MailFactory(MAIL_MAX_WEIGHT, random);
        // System.out.println("Num Mail Items: "+MAIL_TO_CREATE);
        mailCreated = 0;
        complete = false;
        allMail = new HashMap<Integer,ArrayList<MailItem>>();
        this.mailPool = mailPool;
    }

    /**
     * This class initializes all mails and sets their corresponding values,
     * All generated mails will be saved in allMail
     */
    public void generateAllMail(){
        while(!complete){
            MailItem newMail = mailFactory.generateMail();
            int timeToDeliver = newMail.getArrivalTime();
            /** Check if key exists for this time **/
            if(allMail.containsKey(timeToDeliver)){
                /** Add to existing array */
                allMail.get(timeToDeliver).add(newMail);
            }
            else{
                /** If the key doesn't exist then set a new key along with the array of MailItems to add during
                 * that time step.
                 */
                ArrayList<MailItem> newMailList = new ArrayList<MailItem>();
                newMailList.add(newMail);
                allMail.put(timeToDeliver,newMailList);
            }
            /** Mark the mail as created */
            mailCreated++;

            /** Once we have satisfied the amount of mail to create, we're done!*/
            if(mailCreated == MAIL_TO_CREATE){
                complete = true;
            }
        }

    }
    
    /**
     * Given the clock time, put the generated mails into the mailPool.
     * So that the robot will can pick up the mails from the pool.
     */
    public void addToMailPool(){
    	// Check if there are any mail to create
        if(this.allMail.containsKey(Clock.Time())){
            for(MailItem mailItem : allMail.get(Clock.Time())){
                System.out.printf("T: %3d > new addToPool [%s]%n", Clock.Time(), mailItem.toString());
                mailPool.addToPool(mailItem);
            }
        }
    }

}
