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
                                     Integer cookies,
                                     Boolean premiumStatus) {
        boolean result;
        try (Connection connection = entityManager.unwrap(Connection.class);
             CallableStatement callableStatement = connection.prepareCall("{call insert_private_info(?, ?, ?, ?)}")) {
            callableStatement.setString(1, userNickname);
            callableStatement.setString(2, userMail);
            callableStatement.setInt(3, cookies);
            callableStatement.setBoolean(4, premiumStatus);
            callableStatement.execute();
            result = true;
        } catch (Exception e) {
            result = false;
        }

        return result;
    }
}
