package co.com.ceiba.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
@CrossOrigin(origins = "http://localhost:8080")
public class HelloRestController {

    @GetMapping(value = "health", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity processStatus() throws InterruptedException {
        Thread.sleep(10000);
        return new ResponseEntity<>("{\"status\": \"Up\", \"code\": \"200\"}", HttpStatus.OK);
    }

}
