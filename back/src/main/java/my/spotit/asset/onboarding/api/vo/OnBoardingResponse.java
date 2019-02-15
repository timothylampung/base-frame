package my.spotit.asset.onboarding.api.vo;

public class OnBoardingResponse {
    private Boolean onBoarded;
    private String message;

    public OnBoardingResponse(boolean onBoarded, String message) {
        this.onBoarded = onBoarded;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean isOnBoarded() {
        return onBoarded;
    }

    public void setOnBoarded(Boolean onBoarded) {
        this.onBoarded = onBoarded;
    }
}
