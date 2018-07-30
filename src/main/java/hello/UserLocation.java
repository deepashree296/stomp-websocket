package hello;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "user_location")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLocation {

    @Id
    @Field(value="_id")
    private String userId;

    /**
     * location is stored in GeoJSON format.
     * {
     *   "type" : "Point",
     *   "coordinates" : [ x, y ]
     * }
     */
    private GeoJsonPoint location;
    private String userName;

}
