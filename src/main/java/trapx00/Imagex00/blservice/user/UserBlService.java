package trapx00.Imagex00.blservice.user;

import org.springframework.stereotype.Service;
import trapx00.Imagex00.vo.ResultMessage;
import trapx00.Imagex00.vo.user.UserSaveVo;

@Service
public interface UserBlService {
    /**
     * sign up
     *
     * @param userSaveVo the user to be registered
     * @return whether the operation is success or not
     */
    ResultMessage signUp(UserSaveVo userSaveVo);

}
