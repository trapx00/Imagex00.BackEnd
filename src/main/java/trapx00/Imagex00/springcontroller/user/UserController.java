package trapx00.Imagex00.springcontroller.user;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import trapx00.Imagex00.blservice.user.UserBlService;
import trapx00.Imagex00.vo.ResultMessage;
import trapx00.Imagex00.vo.user.UserSaveVo;

@RestController
public class UserController {
    @Autowired
    private UserBlService userBlService;

    @ApiOperation(value = "signUp", nickname = "signUp")
    @RequestMapping(method = RequestMethod.POST, path = "/signUp", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = ResultMessage.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @ResponseBody
    public ResultMessage signUp(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userBlService.signUp(new UserSaveVo(username, password));
    }
}
