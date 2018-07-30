package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Controller
public class GreetingController {


    @Autowired
    DemoService demoService;

    @Autowired
    UserMongoRepository userMongoRepository;


    @RequestMapping(
            value="/user",
            method=RequestMethod.PUT,
            consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<UserData> peopleUserOnboarding(@RequestBody UserData user ) {

        UserData newUser = userMongoRepository.save(user);

        return  new ResponseEntity<UserData>(newUser, HttpStatus.CREATED);

    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }


    @MessageMapping("/location")
    @SendTo("/nearby/users")
    public List<UserLocation> getNearByContacts(UserLocationData userLocationData) {
        return demoService.getNearByContactsOfUser(userLocationData);

    }
}
