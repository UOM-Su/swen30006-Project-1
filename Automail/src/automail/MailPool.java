package automail;

import java.util.LinkedList;
import java.util.Comparator;
import java.util.ListIterator;

import exceptions.ItemTooHeavyException;
import simulation.Simulation;

/**
 * addToPool is called when there are mail items newly arrived at the building to add to the MailPool or
 * if a robot returns with some undelivered items - these are added back to the MailPool.
 * The data structure and algorithms used in the MailPool is your choice.
 * 
 */
public class MailPool {

	private final Charger charger = new Charger();

	private class Item {
		int destination;
		boolean priority;
		MailItem mailItem;
		// Use stable sort to keep arrival time relative positions
		
		public Item(MailItem mailItem) {
			destination = mailItem.getDestFloor();
			priority = false;
			this.mailItem = mailItem;
		}
	}

	// order the mail item according to their destination
	public class ItemComparator implements Comparator<Item> {
		@Override
		public int compare(Item i1, Item i2) {
			int order = 0;
			if (i1.destination < i2.destination) {
				order = 1;
			} else if (i1.destination > i2.destination) {
				order = -1;
			}
			return order;
		}
	}

	// order the mail item according to their priority
	public class ChargeComparator implements Comparator<Item> {
		@Override
		public int compare(Item item1, Item item2) {
			int order = 0;
			if (item1.priority == true && item2.priority == false) {
				order = -1;
			} else if (item1.priority == false && item2.priority == true) {
				order = 1;
			}
			return order;
		}
	}
	
	private LinkedList<Item> pool;
	private LinkedList<Robot> robots;

	public MailPool(int nrobots){
		// Start empty
		pool = new LinkedList<Item>();
		robots = new LinkedList<Robot>();
	}

	/**
     * Adds an item to the mail pool
     * @param mailItem the mail item being added.
     */
	public void addToPool(MailItem mailItem) {
		Item item = new Item(mailItem);
		pool.add(item);

		// It will sort the delivery item according to the item priority if the threshold is on
		if (Simulation.CHARGE_THRESHOLD > 0) {
			double estimateChargeBeforeDelivery = charger.estimateChargeBeforeDelivery(mailItem);
			if (estimateChargeBeforeDelivery > Simulation.CHARGE_THRESHOLD) {
				item.priority = true;
			}
			pool.sort(new ChargeComparator());
		} else {
			pool.sort(new ItemComparator());
		}
	}
	
	
	
	/**
     * load up any waiting robots with mailItems, if any.
     */
	public void loadItemsToRobot() throws ItemTooHeavyException {
		//List available robots
		ListIterator<Robot> i = robots.listIterator();
		while (i.hasNext()) loadItem(i);
	}
	
	//load items to the robot
	private void loadItem(ListIterator<Robot> i) throws ItemTooHeavyException {
		Robot robot = i.next();
		assert(robot.isEmpty());
		// System.out.printf("P: %3d%n", pool.size());
		ListIterator<Item> j = pool.listIterator();
		if (pool.size() > 0) {
			while (robot.spaceForItem()) {
				if (pool.size() == 0) {
					break;
				}
				try {
					robot.load(j.next().mailItem);
					j.remove();
				} catch (Exception e) {
					throw e;
				}
			}
			robot.dispatch();
			i.remove();
		}
	}

	/**
     * @param robot refers to a robot which has arrived back ready for more mailItems to deliver
     */	
	public void registerWaiting(Robot robot) { // assumes won't be there already
		robots.add(robot);
	}

	public Charger getCharger() {
		return charger;
	}
}
