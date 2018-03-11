package trapx00.imagex00.exception.viewexception;

import trapx00.imagex00.vo.response.Response;

public class SystemException extends Exception {
    private Response response = new Response(10001, "System is error.");

    public Response getResponse() {
        return response;
    }
}
