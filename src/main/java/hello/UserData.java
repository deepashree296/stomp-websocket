package hello;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Document(collection = "users")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserData {

    @Id
    @Field(value="_id")
    private String userId;

    private String userName;

    @Field(value="phone_number")
    private String phoneNumber;


    public UserData() {
    }

    public UserData(String userId, String userName, String phone) {
        this.userName = userName;
        this.phoneNumber = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                ", name='" + userName + '\'' +
                ", phone=" + phoneNumber +
                '}';
    }

}
