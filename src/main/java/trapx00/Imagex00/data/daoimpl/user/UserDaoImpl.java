package trapx00.Imagex00.data.daoimpl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trapx00.Imagex00.data.dao.user.UserDao;
import trapx00.Imagex00.entity.user.User;
import trapx00.Imagex00.util.FileUtil;

@Service
public class UserDaoImpl implements UserDao {

    @Override
    public User save(User user) {
        return FileUtil.saveTuple(user, User.class);
    }

    @Override
    public User findUserByUsername(String username) {
        return FileUtil.findOne(username, User.class);
    }
}

