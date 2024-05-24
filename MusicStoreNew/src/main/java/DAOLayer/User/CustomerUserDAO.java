package DAOLayer.User;

import DAOLayer.GenericDAO;
import Entities.User.Customeruser;
import Entities.User.User;
import jakarta.persistence.EntityManager;


public class CustomerUserDAO extends GenericDAO<Customeruser, Integer> {
    GenericDAO<User, Integer> userDAO;

    public CustomerUserDAO(Class<Customeruser> entityClass, GenericDAO<User, Integer> userDAO, EntityManager entityManager) {
        super(entityClass, entityManager);
        this.userDAO = userDAO;
    }

    public boolean insertCustomerUser(Customeruser customeruser, String usermail, String usernickname){
        boolean result;

        try {
            User user = userDAO.findById(customeruser.getId());
            if (user == null) {
                user = new User();
                user.setId(customeruser.getId());
                user.setEmail(usermail);
                user.setNickname(usernickname);
                userDAO.save(user);
            }

            entityManager.getTransaction().begin();
            customeruser.setUsers(user);
            entityManager.persist(customeruser);
            result = true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            result = false;
            System.err.println("--------------------");
            System.err.println("Failed to insert customer user:" + usernickname +
                    "\n"  + e.getMessage());
        }

        return result;
    }
}
