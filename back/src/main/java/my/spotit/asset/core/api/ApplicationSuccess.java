package my.spotit.asset.core.api;

/**
 */
public class ApplicationSuccess {
    private String status;
    private String message;

    public ApplicationSuccess() {
    }

    public ApplicationSuccess(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
