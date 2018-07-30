package hello;


import hello.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;


public interface UserMongoRepository extends MongoRepository<UserData, String> {

    // declare query methods on domain User ( id type is String)
    UserData findByPhoneNumber(String phoneNumber);

    @Query(value="{_id : {$in : ?0}}", fields="{_id : 1, userName : 1}")
    List<UserData> findByListOfId(List<String> userIdList);

    @Query(value="{_id : '?0'}", fields="{userName : 1}")
    UserData findByUserId(String userId);

}