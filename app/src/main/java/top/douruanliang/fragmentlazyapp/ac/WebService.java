package top.douruanliang.fragmentlazyapp.ac;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * author: dourl
 * created on: 2018/9/12 下午3:51
 * description:
 */
public interface WebService {
    @GET("/users/{user}")
    Call<User> getUser(@Path("user") String userId);
}
