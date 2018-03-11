package trapx00.imagex00.springcontroller.user;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trapx00.imagex00.blservice.user.UserBlService;
import trapx00.imagex00.exception.SystemException;
import trapx00.imagex00.exception.UserAlreadyExistsException;
import trapx00.imagex00.vo.response.Response;
import trapx00.imagex00.vo.response.SuccessResponse;
import trapx00.imagex00.vo.user.UserSaveVo;

@RestController
public class UserController {
    @Autowired
    private UserBlService userBlService;

    @ApiOperation(value = "signUp", nickname = "signUp")
    @RequestMapping(method = RequestMethod.POST, path = "/signUp", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = Response.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @ResponseBody
    public ResponseEntity<Response> signUp(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            userBlService.signUp(new UserSaveVo(username, password));
            return new ResponseEntity<>(new SuccessResponse(), HttpStatus.OK);
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getResponse(), HttpStatus.CONFLICT);
        } catch (SystemException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getResponse(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
