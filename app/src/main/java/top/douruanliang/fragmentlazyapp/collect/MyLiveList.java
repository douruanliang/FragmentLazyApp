package top.douruanliang.fragmentlazyapp.collect;

import java.util.List;

/**
 * Created by guohao on 2017/9/6.
 * Description
 */

public class MyLiveList {

    private String title;
    private String source;
    public boolean isSelect;

    private long countTime;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public long getCountTime() {
        return countTime;
    }

    public void setCountTime(long countTime) {
        this.countTime = countTime;
    }
}
