package co.com.ceiba.service;

import co.com.ceiba.exception.TechnicalException;
import org.pmw.tinylog.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestConsumeExample {

    final RestTemplate restTemplate;
    private static final String URL_BASE = "http://localhost:8081/hello/";

    @Autowired
    public RestConsumeExample(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void helloHealth() {
      //  HttpHeaders headers = new HttpHeaders();
      //  headers.setContentType(MediaType.APPLICATION_JSON_VALUE);
      //  HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(URL_BASE + "health/", HttpMethod.GET, HttpEntity.EMPTY, String.class);
            Logger.info("RESPONSE");
            Logger.info("STATUS: " + response.getStatusCodeValue());
            Logger.info("BODY: " + response.getBody());
        }catch (ResourceAccessException e){
            throw new TechnicalException("Servicio demorado, timeout agotado",e);
        }



    }
}
