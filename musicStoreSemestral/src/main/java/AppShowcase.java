import DAOLayer.Product.AccessoryDAO;
import Entities.Product.Accessory;
import ServiceLayer.Product.AccessoryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AppShowcase {
    public static void main(String[] args) {
        // Create EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("music-store-default");
        EntityManager em = emf.createEntityManager();

        // Create DAO and Service instances
        AccessoryDAO accessoryDAO = new AccessoryDAO(Accessory.class, em);
        AccessoryService accessoryService = new AccessoryService(accessoryDAO);

        // Demonstrate assigning accessory to instrument
        String accessoryISBN = "123-456-789";
        String instrumentModelNumber = "ABC-123";

        boolean success = accessoryService.assignAccessoryToInstrument(accessoryISBN, instrumentModelNumber);
        boolean success2 = accessoryService.assignAccessoryToInstrument("9781585816705", "instrument9");

        // Close EntityManager and EntityManagerFactory
        em.close();
        emf.close();

        System.out.println("Accessory assigned to instrument: " + success);
        System.out.println("Accessory assigned to instrument: " + success2);
    }
}
