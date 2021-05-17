package twitter.clone.api.dto;

public class ErrorDto {

    private String error;
    private long timeStamp;
    private int status;
    public ErrorDto() {
    }

    public ErrorDto(String error, long timeStamp, int status) {
        this.error = error;
        this.timeStamp = timeStamp;
        this.status = status;
    }


    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}

    