package automail;

import simulation.IMailDelivery;

public class Automail {
	      
    public Robot[] robots;
    public MailPool mailPool;
    
    public Automail(MailPool mailPool, IMailDelivery delivery, int numRobots) {  	
    	/** Initialize the MailPool */
    	
    	this.mailPool = mailPool;
    	
    	/** Initialize robots */
    	robots = new Robot[numRobots];


    	// Existing system
    	for (int i = 0; i < numRobots; i++) robots[i] = new NormalRobot(delivery, mailPool, i);
    }
    
}
