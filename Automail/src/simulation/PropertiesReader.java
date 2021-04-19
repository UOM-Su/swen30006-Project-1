package simulation;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    /** It will read the properties from the given file */
    public static Properties setUpProperties() throws IOException {
        Properties automailProperties = new Properties();
        // Default properties
        automailProperties.setProperty("Robots", "Standard");
        automailProperties.setProperty("Floors", "10");
        automailProperties.setProperty("Mail_to_Create", "80");
        automailProperties.setProperty("ChargeThreshold", "0");
        automailProperties.setProperty("ChargeDisplay", "false");

        // Read properties
        FileReader inStream = null;
        try {
            inStream = new FileReader("automail.properties");
            automailProperties.load(inStream);
        } finally {
            if (inStream != null) {
                inStream.close();
            }
        }

        // Floors
        Building.FLOORS = Integer.parseInt(automailProperties.getProperty("Floors"));
        System.out.println("#Floors: " + Building.FLOORS);
        // Mail_to_Create
        Simulation.MAIL_TO_CREATE = Integer.parseInt(automailProperties.getProperty("Mail_to_Create"));
        System.out.println("#Created mails: " + Simulation.MAIL_TO_CREATE);
        // Mail_to_Create
        Simulation.MAIL_MAX_WEIGHT = Integer.parseInt(automailProperties.getProperty("Mail_Max_Weight"));
        System.out.println("#Maximum weight: " + Simulation.MAIL_MAX_WEIGHT);
        // Last_Delivery_Time
        Clock.MAIL_RECEVING_LENGTH = Integer.parseInt(automailProperties.getProperty("Mail_Receving_Length"));
        System.out.println("#Mail receiving length: " + Clock.MAIL_RECEVING_LENGTH);
        // Robots
        Simulation.NUM_ROBOTS = Integer.parseInt(automailProperties.getProperty("Robots"));
        System.out.print("#Robots: ");
        System.out.println(Simulation.NUM_ROBOTS);
        assert (Simulation.NUM_ROBOTS > 0);
        // Charge Threshold
        Simulation.CHARGE_THRESHOLD = Double.parseDouble(automailProperties.getProperty("ChargeThreshold"));
        System.out.println("#Charge Threshold: " + Simulation.CHARGE_THRESHOLD);
        // Charge Display
        Simulation.CHARGE_DISPLAY = Boolean.parseBoolean(automailProperties.getProperty("ChargeDisplay"));
        System.out.println("#Charge Display: " + Simulation.CHARGE_DISPLAY);

        return automailProperties;
    }
}
