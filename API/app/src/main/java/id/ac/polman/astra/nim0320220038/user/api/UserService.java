// UserService.java
package id.ac.polman.astra.nim0320220038.user.api;

import java.util.List;

import id.ac.polman.astra.nim0320220038.user.api.UserVO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface UserService {
    @GET("userbyid")
    Call<UserVO> getUserById(@Query("id") String id);

    @GET("userlist")
    Call<List<UserVO>> getUsers();

    @POST("save")
    Call<UserVO> addUser(@Body UserVO user);

    @PUT("save")
    Call<UserVO> updateuser(@Body UserVO user);

    @DELETE("save")
    Call<UserVO> deleteUserById(@Query("id") String id);
}
