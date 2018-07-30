package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    UserLocationRepository userLocationRepository;

    @Autowired
    UserMongoRepository userMongoRepository;

    @Value("${nearby.users.radius}")
    private String radius;

    @Override
    public List<UserLocation> getNearByContactsOfUser(UserLocationData userLocationData) {

        Double latitude = Double.valueOf(userLocationData.getLatitude());
        Double longitude = Double.valueOf(userLocationData.getLongitude());

        // find users in the nearby radius for this location
        Distance distance = new Distance(Double.valueOf(radius), Metrics.KILOMETERS);
        Point point = new Point(longitude,longitude);
        List<UserLocation> nearByUserInfo = userLocationRepository.findByLocationNear(point, distance);


        // save user location in database
        GeoJsonPoint currentLocation = new GeoJsonPoint(Double.valueOf(userLocationData.getLongitude()), Double.valueOf(userLocationData.getLatitude()));
        UserLocation userLocation = new UserLocation();
        userLocation.setLocation(currentLocation);
        userLocation.setUserId(userLocationData.getUserId());

        UserData userInfo = userMongoRepository.findByUserId(userLocationData.getUserId());
        userLocation.setUserName(userInfo.getUserName());
        userLocationRepository.save(userLocation);

        return nearByUserInfo;
    }
}
