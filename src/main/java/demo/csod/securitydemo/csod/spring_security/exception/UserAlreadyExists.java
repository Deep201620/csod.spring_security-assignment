package demo.csod.securitydemo.csod.spring_security.exception;

public class UserAlreadyExists extends RuntimeException{

    private int errorCode;
    private String message;

    public UserAlreadyExists(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
