package trapx00.imagex00.vo.response;

public class Response {
    private int status;
    private String object;

    public Response(int status, String object) {
        this.status = status;
        this.object = object;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
