package ServiceLayer.User;

import DAOLayer.GenericDAOInterface;
import DAOLayer.User.PrivateInfoDAO;
import Entities.User.PrivateInfo;
import ServiceLayer.GenericService;

public class PrivateInfoService extends GenericService<PrivateInfo, Integer> {
    PrivateInfoDAO privateInfoDAO = null;

    public PrivateInfoService(GenericDAOInterface<PrivateInfo, Integer> dao) {
        super(dao);
        if (!(dao instanceof PrivateInfoDAO))
            throw new IllegalArgumentException("PrivateInfoService should be constructed with a PrivateInfoDAO");
        else {
            this.privateInfoDAO = (PrivateInfoDAO) dao;
        }
    }

    public boolean insertPrivateInfo(PrivateInfo privateInfo) {
        return privateInfoDAO.insertPrivateInfo(
                privateInfo.getId().getUsernickname(),
                privateInfo.getId().getUsermail(),
                privateInfo.getId().getFullname(),
                privateInfo.getHomenumber(),
                privateInfo.getId().getStreet(),
                privateInfo.getId().getCity(),
                privateInfo.getId().getZip(),
                privateInfo.getId().getPhonenumber(),
                privateInfo.getPassword());
    }

    public boolean insertPrivateInfo(String userNickname,
                                     String userMail,
                                     String fullName,
                                     String homeNumber,
                                     String street,
                                     String city,
                                     String zip,
                                     String phoneNumber,
                                     String password) {
        return privateInfoDAO.insertPrivateInfo(userNickname, userMail, fullName, homeNumber, street, city, zip, phoneNumber, password);
    }


}
