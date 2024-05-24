package ServiceLayer.Product;

import DAOLayer.GenericDAOInterface;
import DAOLayer.Product.AccessoryDAO;
import Entities.Product.Accessory;
import Entities.Product.Instrumentproduct;
import ServiceLayer.GenericService;

import java.util.stream.Collectors;

public class AccessoryService extends GenericService<Accessory, Integer> {
    private AccessoryDAO accessoryDAO = null;

    public AccessoryService(GenericDAOInterface<Accessory, Integer> dao) {
        super(dao);
        if (!(dao instanceof AccessoryDAO))
            throw new IllegalArgumentException("AccessoryService should be constructed with a AccessoryDAO");
        else {
            this.accessoryDAO = (AccessoryDAO) dao;
        }
    }

    public boolean assignAccessoryToInstrument(Accessory accessory, Instrumentproduct instrument) {
        return accessoryDAO.assignAccessoryToProduct(accessory.getId() , instrument.getId());
    }

    public void printAllAccessoryTypesAmmount(){
        accessoryDAO.findAll().stream()
                .map(Accessory::getType)
                .collect(Collectors.toMap(type -> type, type -> 1, Integer::sum))
                .forEach((type, ammount) -> System.out.println(type + " : " + ammount));
    }
}
