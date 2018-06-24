package top.douruanliang.fragmentlazyapp.net.impl;

import top.douruanliang.fragmentlazyapp.domain.IpInfo;
import top.douruanliang.fragmentlazyapp.net.IpInfoContract;
import top.douruanliang.fragmentlazyapp.net.LoadTaskCallBack;
import top.douruanliang.fragmentlazyapp.net.NetTask;

public class IpInfoPresenter implements IpInfoContract.Presenter ,LoadTaskCallBack<IpInfo>{

    private NetTask mNetTask;

    private IpInfoContract.View addTaskView;


    public IpInfoPresenter(NetTask netTask, IpInfoContract.View addTaskView) {
        mNetTask = netTask;
        this.addTaskView = addTaskView;
    }

    @Override
    public void getIpInfo(String ip) {
        mNetTask.execute(ip,this);
    }

    @Override
    public void onStart() {
        if (addTaskView.isActive()){
            addTaskView.showLoading();
        }
    }

    @Override
    public void onFailed() {

        if (addTaskView.isActive()){
            addTaskView.showError();
            addTaskView.hideLoading();
        }

    }

    @Override
    public void onSuccess(IpInfo ipInfo) {
        if (addTaskView.isActive()){
            addTaskView.setIpInfo(ipInfo);
        }

    }

    @Override
    public void onFinish() {

        if (addTaskView.isActive()){
            addTaskView.hideLoading();
        }
    }
}
