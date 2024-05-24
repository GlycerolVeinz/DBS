package DAOLayer.Product;

import DAOLayer.GenericDAO;
import Entities.Product.Accessory;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;

import java.sql.CallableStatement;
import java.sql.Connection;

public class AccessoryDAO extends GenericDAO<Accessory, Integer> {

    public AccessoryDAO(Class<Accessory> entityClass, EntityManager entityManager) {
        super(entityClass, entityManager);
    }

    public boolean assignAccessoryToProduct(String accessoryISBN, String instrumentModelNumber) {
        boolean result;
        try (Connection connection = entityManager.unwrap(Connection.class);
             CallableStatement callableStatement = connection.prepareCall("{call assign_accessory_to_product(?, ?)}")) {
            callableStatement.setString(1, accessoryISBN);
            callableStatement.setString(2, instrumentModelNumber);
            callableStatement.execute();
            result = true;

        } catch (Exception e) {
            result = false;
            System.err.println("--------------------");
            System.err.println("Failed to assign accessory:" + accessoryISBN +
                    " to product: " + instrumentModelNumber +
                    "\n"  + e.getMessage());

        }

        return result;
    }
}
