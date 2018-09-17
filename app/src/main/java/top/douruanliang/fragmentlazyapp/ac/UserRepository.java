package top.douruanliang.fragmentlazyapp.ac;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author: dourl
 * created on: 2018/9/12 下午3:50
 * description:
 */
public class UserRepository {
    private final WebService mWebService;
    private final UserDao mUserDao;
    private final Executor mExecutor;

    private UserCache mUserCache;

    @Inject
    public UserRepository(WebService webService, UserDao userDao, Executor executor) {
        mWebService = webService;
        mUserDao = userDao;
        mExecutor = executor;
    }

    public LiveData<User> getUser(String userId){

        //先从缓存获取数据
        final MutableLiveData<User> data = new MutableLiveData<>();

        //放入缓存

        mWebService.getUser(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        return data;
    }

    private void refreshUser(final String userId){
         mExecutor.execute(()->{
             boolean userExists = false;
             if (!userExists){
                 try {
                     Response response = mWebService.getUser(userId).execute();
                     mUserDao.save((User)response.body());
                 } catch (IOException e) {
                     e.printStackTrace();
                 }


             }
         });
    }
}
