package top.douruanliang.fragmentlazyapp.model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

/**
 * author: dourl
 * created on: 2018/8/6 上午9:53
 * description:
 */
public class NameViewModle extends ViewModel {

    private MutableLiveData<String> mCurrentName;

    private MutableLiveData<List<String>> mNameList;

    public MutableLiveData<String> getCurrentName() {

        if (mCurrentName == null) {
            mCurrentName = new MutableLiveData<>();
        }
        return mCurrentName;
    }

    public MutableLiveData<List<String>> getNameList() {
        if (mCurrentName == null) {
            mCurrentName = new MutableLiveData<>();
        }
        return mNameList;
    }
}
