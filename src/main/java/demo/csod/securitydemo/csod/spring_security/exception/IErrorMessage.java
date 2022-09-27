package demo.csod.securitydemo.csod.spring_security.exception;

public interface IErrorMessage {

     String  USER_NOT_EXIST = "User does not exist";
     String USER_EXISTS = "User already exists with the same emailId";
     String METHOD_NOT_SUPPORTED= " method is not supported with following parameters";
     String CHECK_FIELDS = "Some fields are not filled with valid data. please look into it.";
     String NULL_FIELDS = "Data cannot be null. Please provide some data";
     String SOMETHING_WENT_WRONG = "Something went wrong. please try again later";
}
