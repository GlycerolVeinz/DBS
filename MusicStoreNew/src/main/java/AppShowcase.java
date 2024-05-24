import DAOLayer.GenericDAO;
import DAOLayer.Product.AccessoryDAO;
import Entities.Product.Accessory;
import Entities.Product.Instrumentproduct;
import Entities.Product.Pickup;
import Entities.User.User;
import ServiceLayer.Product.AccessoryService;
import ServiceLayer.TransactionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AppShowcase {
    public static void main(String[] args) {
        // Create EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("music-store-default");
        EntityManager em = emf.createEntityManager();

//        GenericDAO<Instrumentproduct, Integer> instrumentDAO = new GenericDAO<>(Instrumentproduct.class, em);
//        var instrument = instrumentDAO.findById(1);
//        System.out.println(instrument);

//        GenericDAO<User, Integer> userDAO = new GenericDAO<>(User.class, em);
//        var user = userDAO.findById(1);
//        System.out.println(user);


        TransactionService transactionService = new TransactionService(
                new GenericDAO<>(Entities.User.Customeruser.class, em),
                new GenericDAO<>(Entities.Product.Product.class, em),
                new GenericDAO<>(Entities.Product.Buy.class, em)
        );

        transactionService.executeTransaction(11, 7);


        em.close();
        emf.close();

//        System.out.println("Accessory assigned to instrument: " + success);
//        System.out.println("Accessory assigned to instrument: " + success2);
    }
}
