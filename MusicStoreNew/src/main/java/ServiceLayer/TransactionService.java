package ServiceLayer;

import DAOLayer.GenericDAO;
import DAOLayer.GenericDAOInterface;
import Entities.Product.Buy;
import Entities.Product.Product;
import Entities.User.Customeruser;
import jakarta.persistence.EntityTransaction;

public class TransactionService{
    GenericDAOInterface<Customeruser, Integer> customerDAO = null;
    GenericDAOInterface<Product, Integer> productDAO = null;
    GenericDAOInterface<Buy, Integer> buyDAO = null;

    public TransactionService(GenericDAOInterface<Customeruser, Integer> customerDAO,
                              GenericDAOInterface<Product, Integer> productDAO,
                              GenericDAOInterface<Buy, Integer> buyDAO) {

        this.customerDAO = (GenericDAO<Customeruser, Integer>) customerDAO;
        this.productDAO = (GenericDAO<Product, Integer>) productDAO;
        this.buyDAO = (GenericDAO<Buy, Integer>) buyDAO;
    }

    public boolean executeTransaction(int customerId, int productId) {
        boolean result = false;

        EntityTransaction transaction = customerDAO.getEntityManager().getTransaction();

        try {
            transaction.begin();

//            Getting customer
            Customeruser customer = customerDAO.findById(customerId);
            if (customer == null) {
                throw new Exception("Customer not found");
            }

//            Getting product
            Product product = productDAO.findById(productId);
            if (product == null) {
                throw new Exception("Product not found");
            }

//            Creating buy and inserting into database
            Buy buy = new Buy();
            buy.setCustomerid(customer);
            buy.setProductid(product);
            buyDAO.save(buy);

//            update customer premium status to true
            customer.setPremiumstatus(true);
            customerDAO.update(customer);

            transaction.commit();
            result = true;

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }

        return result;
    }
}
