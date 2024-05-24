package DAOLayer.Product;

import DAOLayer.GenericDAO;
import Entities.Product.Accessory;
import jakarta.persistence.EntityManager;

public class AccessoryDAO extends GenericDAO<Accessory, Integer> {
    public AccessoryDAO(Class<Accessory> entityClass, EntityManager entityManager) {
        super(entityClass, entityManager);
    }

    public boolean assignAccessoryToProduct(int accessoryID, int instrumentID) {
        boolean result;
        String sql = "SELECT assign_accessory_and_instrument(?, ?)";
        try {
            entityManager.getTransaction().begin();
            entityManager.createNativeQuery(sql)
                    .setParameter(1, accessoryID)
                    .setParameter(2, instrumentID)
                    .executeUpdate();
            entityManager.getTransaction().commit();
            result = true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            result = false;
            System.err.println("--------------------");
            System.err.println("Failed to assign accessory:" + accessoryID +
                    " to product: " + instrumentID +
                    "\n"  + e.getMessage());
        }

        return result;
    }
}
