import DAOLayer.GenericDAO;
import DAOLayer.Product.AccessoryDAO;
import Entities.Product.Accessory;
import Entities.Store.Store;
import ServiceLayer.GenericService;
import ServiceLayer.Product.AccessoryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AppShowcase {
    public static void main(String[] args) {
        // Create EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("music-store-default");
        EntityManager em = emf.createEntityManager();

//        AccessoryService accessoryService = new AccessoryService(new AccessoryDAO(Accessory.class, em));
//        accessoryService.printAllAccessoryTypesAmmount();

        Store store = new Store();
//        store.setId(42);
//        store.setLocation("Amsterdam/42");
        GenericService<Store, Integer> storeService = new GenericService<>(new GenericDAO<Store, Integer>(Store.class, em));
        boolean success = storeService.save(store);
        System.out.println("Store inserted: " + success);
        store.setLocation("Kyiv/Shevchenkivska");
        storeService.update(store);
        System.out.println("Store updated: " + success);
//        storeService.delete(store);


//        AccessoryDAO accessoryDAO = new AccessoryDAO(Accessory.class, em);
//        AccessoryService accessoryService = new AccessoryService(accessoryDAO);
//        boolean success = accessoryDAO.assignAccessoryToProduct(27004, 1);
//
//        GenericDAO<Instrumentproduct, Integer> instrumentDAO = new GenericDAO<>(Instrumentproduct.class, em);
//        var instrument = instrumentDAO.findById(1);
//        System.out.println(instrument);
//
//        GenericDAO<User, Integer> userDAO = new GenericDAO<>(User.class, em);
//        var user = userDAO.findById(1);
//        System.out.println(user);


//        TransactionService transactionService = new TransactionService(
//                new GenericDAO<>(Entities.User.Customeruser.class, em),
//                new GenericDAO<>(Entities.Product.Product.class, em),
//                new GenericDAO<>(Entities.Product.Buy.class, em)
//        );
//
//        transactionService.executeTransaction(11, 7);

//        CustomerUserDAO customerUserDAO = new CustomerUserDAO(Customeruser.class, new GenericDAO<>(User.class, em), em);
//        CustomerService customerService = new CustomerService(customerUserDAO);
//        Customeruser customer = new Customeruser();
//        customer.setId(201);
//        customer.setPremiumstatus(false);
//        customer.setCookies(201);
//        boolean success2 = customerService.insertCustomerUser(customer, "cus201@seznam.cz", "customer201");
//        System.out.println("Customer inserted: " + success2);

//        GenericDAO<Pickup, Integer> pickupDAO = new GenericDAO<>(Pickup.class, em);
//        Pickup pickup = new Pickup();
//        pickup.setId(20);
//        pickup.setName("piezo");
//        boolean success = pickupDAO.save(pickup);
//        System.out.println("Pickup inserted: " + success);

        em.close();
        emf.close();

    }
}
