package top.douruanliang.fragmentlazyapp.net;


import top.douruanliang.fragmentlazyapp.domain.IpInfo;

/**
 * 契约接口 主要用来存储相同业务的Presenter and view interdace
 */
public interface IpInfoContract {


    interface  Presenter{
        void getIpInfo(String ip);
    }


    interface  View extends BaseView<Presenter>{


        void setIpInfo(IpInfo ipInfo);
        void showLoading();

        void hideLoading();

        void showError();

        boolean isActive();
    }
}
