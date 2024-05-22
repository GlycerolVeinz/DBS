package DAOLayer.User;

import DAOLayer.GenericDAO;
import Entities.User.PrivateInfo;
import jakarta.persistence.EntityManager;

import java.sql.CallableStatement;
import java.sql.Connection;

public class PrivateInfoDAO extends GenericDAO<PrivateInfo, Integer> {
    public PrivateInfoDAO(EntityManager entityManager) {
        super(PrivateInfo.class, entityManager);
    }

    public boolean insertPrivateInfo(String userNickname,
                                     String userMail,
                                     String fullName,
                                     String homeNumber,
                                     String street,
                                     String city,
                                     String zip,
                                     String phoneNumber,
                                     String password){
        boolean result;
        try (Connection connection = entityManager.unwrap(Connection.class);
             CallableStatement callableStatement = connection.prepareCall("{call insertprivateinfo(?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {
            callableStatement.setString(1, userNickname);
            callableStatement.setString(2, userMail);
            callableStatement.setString(3, fullName);
            callableStatement.setString(4, homeNumber);
            callableStatement.setString(5, street);
            callableStatement.setString(6, city);
            callableStatement.setString(7, zip);
            callableStatement.setString(8, phoneNumber);
            callableStatement.setString(9, password);
            callableStatement.execute();
            result = true;
        } catch (Exception e) {
            result = false;
            System.err.println("Failed to insert private info: " + e.getMessage());
        }

        return result;
    }
}
