package ServiceLayer.User;

import DAOLayer.GenericDAOInterface;
import DAOLayer.User.CustomerUserDAO;
import Entities.User.Customeruser;
import ServiceLayer.GenericService;

public class CustomerService extends GenericService<Customeruser, Integer> {
    CustomerUserDAO customerUserDAO;

    public CustomerService(GenericDAOInterface<Customeruser, Integer> dao) {
        super(dao);
        if (dao instanceof CustomerUserDAO) {
            this.customerUserDAO = (CustomerUserDAO) dao;
        } else {
            throw new IllegalArgumentException("CustomerService requires CustomerUserDAO");
        }
    }

    public boolean insertCustomerUser(Customeruser customeruser, String usermail, String usernickname){
        return customerUserDAO.insertCustomerUser(customeruser, usermail, usernickname);
    }
}
