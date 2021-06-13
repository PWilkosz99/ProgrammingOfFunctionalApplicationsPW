package RESTAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {
    @GetMapping
    public String getControllerInfo() {
        return "camel-controller";
    }
}
