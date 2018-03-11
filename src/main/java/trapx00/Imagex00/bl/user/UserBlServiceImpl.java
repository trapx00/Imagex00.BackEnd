package trapx00.Imagex00.bl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trapx00.Imagex00.blservice.user.UserBlService;
import trapx00.Imagex00.dataservice.user.UserDataService;
import trapx00.Imagex00.util.Convertor;
import trapx00.Imagex00.vo.ResultMessage;
import trapx00.Imagex00.vo.user.UserSaveVo;

@Service
public class UserBlServiceImpl implements UserBlService {

    @Autowired
    private UserDataService userDataService;

    /**
     * sign up
     *
     * @param userSaveVo the user to be registered
     * @return whether the operation is success or not
     */
    @Override
    public ResultMessage signUp(UserSaveVo userSaveVo) {
        if (!userDataService.isTheUserExists(userSaveVo.getUsername())) {
            return userDataService.saveUser(Convertor.userSaveVoToUser(userSaveVo));
        } else {
            return ResultMessage.DataError;
        }
    }
}
