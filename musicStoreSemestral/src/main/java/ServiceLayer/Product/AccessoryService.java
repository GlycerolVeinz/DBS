package ServiceLayer.Product;

import DAOLayer.GenericDAOInterface;
import DAOLayer.Product.AccessoryDAO;
import Entities.Product.Accessory;
import ServiceLayer.GenericService;

public class AccessoryService extends GenericService<Accessory, Integer> {
    AccessoryDAO accessoryDAO = null;

    public AccessoryService(GenericDAOInterface<Accessory, Integer> dao) {
        super(dao);
        if (!(dao instanceof AccessoryDAO))
            throw new IllegalArgumentException("AccessoryService should be constructed with a AccessoryDAO");
        else {
            this.accessoryDAO = (AccessoryDAO) dao;
        }
    }

    public boolean assignAccessoryToInstrument(String accessoryISBN, String instrumentModelNumber) {
        return accessoryDAO.assignAccessoryToProduct(accessoryISBN, instrumentModelNumber);
    }
}
