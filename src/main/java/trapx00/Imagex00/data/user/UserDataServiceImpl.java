package trapx00.Imagex00.data.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trapx00.Imagex00.data.dao.user.UserDao;
import trapx00.Imagex00.dataservice.user.UserDataService;
import trapx00.Imagex00.entity.user.User;
import trapx00.Imagex00.vo.ResultMessage;

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
    public ResultMessage saveUser(User user) {
        if (userDao.save(user) != null) {
            return ResultMessage.Success;
        } else {
            return ResultMessage.SystemError;
        }
    }
}
