package top.douruanliang.fragmentlazyapp.ac;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;


/**
 * author: dourl
 * created on: 2018/9/12 下午3:05
 * description:
 */
public class UserProfileViewModel extends ViewModel{

    /*private User user;
    public User getUser(){
        return this.user;
    }*/


    private String userId;
    private LiveData<User> user;
    //数据层抽象
    private UserRepository mUserRepository;

    @Inject
    public UserProfileViewModel(UserRepository userRepository) {
        mUserRepository = userRepository;
    }

    public void init(String userId){
        this.userId = userId;
        if (this.user !=null){
            return;
        }
        user = mUserRepository.getUser(userId);
    }

    public LiveData<User> getUser(){
        return this.user;
    }

}
