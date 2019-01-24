package co.com.ceiba.controller;

        import co.com.ceiba.service.RestConsumeExample;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.MediaType;
        import org.springframework.web.bind.annotation.CrossOrigin;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:8080")
public class RestConsumeExampleService {

    @Autowired
    RestConsumeExample restConsumeExample;

    @GetMapping(value = "hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public void processStatus() {
        restConsumeExample.helloHealth();
    }

}
