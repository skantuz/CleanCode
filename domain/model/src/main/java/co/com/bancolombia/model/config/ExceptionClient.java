package co.com.bancolombia.model.config;

import lombok.Getter;

@Getter
public class ExceptionClient extends RuntimeException {
    private String title;
    private int status;
    private String code;
    private String log;

    public ExceptionClient(String log, String title, int status, String message,String code) {
        super(message);
        this.log = log;
        this.title = title;
        this.status = status;
        this.code = code;
    }

    public ExceptionClient(String message) {
        super(message);
    }
}
