package trapx00.imagex00.exception;

import trapx00.imagex00.vo.response.Response;

public class UserAlreadyExistsException extends Exception {
    private Response response = new Response(10002, "UserAlreadyExists");

    public Response getResponse() {
        return response;
    }
}