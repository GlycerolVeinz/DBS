package ServiceLayer.User;

import DAOLayer.GenericDAO;
import DAOLayer.GenericDAOInterface;
import DAOLayer.User.WorkerUserDAO;
import Entities.User.WorkerUser;
import ServiceLayer.GenericService;

import java.util.List;

public class WorkerUserService extends GenericService<WorkerUser, Integer> {
    private WorkerUserDAO workerUserDAO = null;

    public WorkerUserService(GenericDAOInterface<WorkerUser, Integer> dao) {
        super(dao);
        if (!(dao instanceof WorkerUserDAO))
            throw new IllegalArgumentException("WorkerUserService should be constructed with a WorkerUserDAO");
        else {
            this.workerUserDAO = (WorkerUserDAO) dao;
        }
    }

    public void setWorkerUserDAO(WorkerUserDAO workerUserDAO) {
        this.workerUserDAO = workerUserDAO;
    }

    public WorkerUserDAO getWorkerUserDAO() {
        return workerUserDAO;
    }

    public boolean insertWorkerUser(WorkerUser workerUser) {
        if (workerUserDAO == null)
            throw new IllegalArgumentException("WorkerUserDAO is not set, you should have listened to the constructor");
        return workerUserDAO.insertWorkerUser(
                workerUser.getPersonalidentificationnumber(),
                workerUser.getId().getUsernickname(),
                workerUser.getId().getUsermail(),
                workerUser.getStore().getLocation());
    }

    public boolean insertWorkerUser(String personalIdentificationNumber,
                                    String userNickname,
                                    String userMail,
                                    String location) {
        if (workerUserDAO == null)
            throw new IllegalArgumentException("WorkerUserDAO is not set, you should have listened to the constructor");
        return workerUserDAO.insertWorkerUser(personalIdentificationNumber, userNickname, userMail, location);
    }

    public boolean insertWorkerUsers(List<WorkerUser> workerUsers){
        if (workerUserDAO == null)
            throw new IllegalArgumentException("WorkerUserDAO is not set, you should have listened to the constructor");

        boolean result = true;
        for (WorkerUser workerUser : workerUsers) {
            boolean current = workerUserDAO.insertWorkerUser(
                    workerUser.getPersonalidentificationnumber(),
                    workerUser.getId().getUsernickname(),
                    workerUser.getId().getUsermail(),
                    workerUser.getStore().getLocation());
            if (!current)
                System.out.println("Failed to insert worker user: " +
                                workerUser.getId().getUsernickname() + " " +
                                workerUser.getId().getUsermail() + " " +
                                workerUser.getStore().getLocation());

            result = result && current;
        }

        return result;
    }
}
