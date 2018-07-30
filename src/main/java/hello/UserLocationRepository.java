package hello;


import hello.UserLocation;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserLocationRepository extends MongoRepository<UserLocation, String> {

    @Query(fields = "{userName : 1}")
    List<UserLocation> findByLocationNear(Point p, Distance d);



}
