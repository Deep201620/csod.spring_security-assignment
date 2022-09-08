package demo.csod.securitydemo.csod.spring_security.exception;

public class ResourceNotFound extends RuntimeException {

    private String errorMessage;
    private String resourceId;


    public ResourceNotFound(String resourcename, String resourceId) {
        this.errorMessage = resourcename;
        this.resourceId = resourceId;

    }
}
