package co.com.ceiba.exception;

import org.pmw.tinylog.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.concurrent.ConcurrentHashMap;


@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String GENERAL_ERROR = "Ha ocurrido un error en el servidor, por favor contacte al administrador";
    private static final ConcurrentHashMap<String, Integer> STATUS_CODES = new ConcurrentHashMap<>();

    public ExceptionHandler() {

    }

    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
    public final ResponseEntity<ServiceResponse> handleAllExceptions(Exception exception) {
        ResponseEntity<ServiceResponse> result;
        final String exceptionName = exception.getClass().getSimpleName();
        final String message = exception.getMessage();
        final Integer code = STATUS_CODES.get(exceptionName);
        if (code != null) {
            final ServiceResponse response = new ServiceResponse(code, exceptionName, message);
            result = new ResponseEntity<>(response, HttpStatus.valueOf(code));
            Logger.error(exception, "Domain Error --> {}, {} ", exceptionName, exception.getMessage());
        } else {
            final ServiceResponse response = new ServiceResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exceptionName, GENERAL_ERROR);
            result = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            Logger.error(exception, "General System Error --> {}, {} ", exceptionName, exception.getMessage());
        }
        return result;
    }
}
