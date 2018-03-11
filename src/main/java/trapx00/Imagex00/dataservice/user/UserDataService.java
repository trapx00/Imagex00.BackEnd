package trapx00.Imagex00.dataservice.user;

import org.springframework.stereotype.Service;
import trapx00.Imagex00.entity.user.User;
import trapx00.Imagex00.vo.ResultMessage;

@Service
public interface UserDataService {
    /**
     * find whether the user exists
     *
     * @param username the username
     * @return whether the user exists
     */
    boolean isTheUserExists(String username);

    /**
     * save the user
     *
     * @param user the user to be saved
     * @return whether the operation is success or not
     */
    ResultMessage saveUser(User user);
}
