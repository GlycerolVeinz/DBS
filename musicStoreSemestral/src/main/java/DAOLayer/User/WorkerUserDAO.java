package DAOLayer.User;

import DAOLayer.GenericDAO;
import Entities.User.WorkerUser;
import jakarta.persistence.EntityManager;

import java.sql.CallableStatement;
import java.sql.Connection;

public class WorkerUserDAO extends GenericDAO<WorkerUser, Integer> {
    public WorkerUserDAO(EntityManager entityManager) {
        super(WorkerUser.class, entityManager);
    }

    public boolean insertWorkerUser(String personalIdentificationNumber,
                                    String userNickname,
                                    String userMail,
                                    String location) {
        boolean result;

        try (Connection connection = entityManager.unwrap(Connection.class);
             CallableStatement callableStatement = connection.prepareCall("{call insert_worker_user(?, ?, ?, ?)}")) {
            callableStatement.setString(1, personalIdentificationNumber);
            callableStatement.setString(2, userNickname);
            callableStatement.setString(3, userMail);
            callableStatement.setString(4, location);
            callableStatement.execute();
            result = true;
        } catch (Exception e) {
            result = false;
            System.err.println("Failed to insert worker user: " + e.getMessage());
        }

        return result;
    }
}
