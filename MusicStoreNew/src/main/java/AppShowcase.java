import DAOLayer.GenericDAO;
import DAOLayer.Product.AccessoryDAO;
import DAOLayer.User.CustomerUserDAO;
import Entities.Product.Accessory;
import Entities.Product.Instrumentproduct;
import Entities.User.Customeruser;
import Entities.User.User;
import Entities.Store.Store;
import ServiceLayer.GenericService;
import ServiceLayer.Product.AccessoryService;
import ServiceLayer.TransactionService;
import ServiceLayer.User.CustomerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AppShowcase {
    public static void main(String[] args) {
        // Create EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("music-store-default");
        EntityManager em = emf.createEntityManager();

//        Print all ===================================================================================================
//        AccessoryService accessoryService = new AccessoryService(new AccessoryDAO(Accessory.class, em));
//        accessoryService.printAllAccessoryTypesAmmount();

//        Save, Update, Delete =========================================================================================
//
//        GenericService<Store, Integer> storeService = new GenericService<>(new GenericDAO<Store, Integer>(Store.class, em));
//        Store store = (storeService.findById(690)).get();
//        System.out.println(store);

//        boolean success = storeService.save(store);
//        System.out.println("Store inserted: " + success + store);
//
//        store.setLocation("Kyiv/Shevchenkivska");
//        boolean success2 = storeService.update(store);
//        System.out.println("Store updated: " + success2 + store);

//        boolean success3 = storeService.delete(store);
//        System.out.println("Store deleted: " + success3);

//        Parametrized query ==========================================================================================
//        AccessoryDAO accessoryDAO = new AccessoryDAO(Accessory.class, em);
//        AccessoryService accessoryService = new AccessoryService(accessoryDAO);
//        boolean success = accessoryDAO.assignAccessoryToProduct(27004, 1);


//        Find by id ==================================================================================================
//        GenericDAO<Instrumentproduct, Integer> instrumentDAO = new GenericDAO<>(Instrumentproduct.class, em);
//        var instrument = instrumentDAO.findById(1);
//        System.out.println(instrument);

//        GenericDAO<User, Integer> userDAO = new GenericDAO<>(User.class, em);
//        var user = userDAO.findById(1);
//        System.out.println(user);

//        Transaction =================================================================================================
//        TransactionService transactionService = new TransactionService(
//                new GenericDAO<>(Entities.User.Customeruser.class, em),
//                new GenericDAO<>(Entities.Product.Product.class, em),
//                new GenericDAO<>(Entities.Product.Buy.class, em)
//        );
//        boolean success = transactionService.executeTransaction(20, 1);
//        System.out.println("Transaction executed: " + success);

//        Inheritance =================================================================================================
        CustomerUserDAO customerUserDAO = new CustomerUserDAO(Customeruser.class, new GenericDAO<>(User.class, em), em);
        CustomerService customerService = new CustomerService(customerUserDAO);
        Customeruser customer = new Customeruser();
        customer.setId(201);
        customer.setPremiumstatus(false);
        customer.setCookies(201);
        boolean success2 = customerService.insertCustomerUser(customer, "cus201@seznam.cz", "customer201");
        System.out.println("Customer inserted: " + success2);

//        Upadate more then one ========================================================================================



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
