package model;

public class Response {
    private String response;
    private String reason;
    private String value;

    public Response() {
    }

    public Response(String response, String value) {
        this.response = response;
        this.value = value;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
