package hello;


import org.springframework.stereotype.Service;

import java.util.List;

public interface DemoService {

    List<UserLocation> getNearByContactsOfUser(UserLocationData userLocationData);
}
