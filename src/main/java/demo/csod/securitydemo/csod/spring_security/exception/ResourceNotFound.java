package demo.csod.securitydemo.csod.spring_security.exception;

public class ResourceNotFound extends RuntimeException {

    private String errorMessage;
    private String resourceId;


    public ResourceNotFound(String errorMessage, String resourceId) {
        this.errorMessage = errorMessage;
        this.resourceId = resourceId;
    }
}
