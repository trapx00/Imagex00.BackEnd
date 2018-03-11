package trapx00.Imagex00.util;

import trapx00.Imagex00.entity.user.User;
import trapx00.Imagex00.vo.user.UserSaveVo;

public class Convertor {
    /**
     * convert userSaveVo to user
     *
     * @param userSaveVo the userSaveVo
     * @return the user
     */
    public static User userSaveVoToUser(UserSaveVo userSaveVo) {
        return new User(userSaveVo.getUsername(), userSaveVo.getPassword());
    }
}
