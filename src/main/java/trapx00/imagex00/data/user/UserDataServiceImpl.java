package trapx00.imagex00.data.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trapx00.imagex00.data.dao.user.UserDao;
import trapx00.imagex00.dataservice.user.UserDataService;
import trapx00.imagex00.entity.user.User;
import trapx00.imagex00.exception.SystemException;

@Service
public class UserDataServiceImpl implements UserDataService {
    @Autowired
    private UserDao userDao;


    /**
     * find whether the user exists
     *
     * @param username the username
     * @return whether the user exists
     */
    @Override
    public boolean isTheUserExists(String username) {
        return userDao.findUserByUsername(username) != null;
    }

    /**
     * save the user
     *
     * @param user the user to be saved
     * @return whether the operation is success or not
     */
    @Override
    public void saveUser(User user) throws SystemException {
        if (userDao.save(user) == null) {
            throw new SystemException();
        }
    }
}
