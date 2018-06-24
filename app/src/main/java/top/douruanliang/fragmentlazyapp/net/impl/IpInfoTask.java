package top.douruanliang.fragmentlazyapp.net.impl;

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;
import top.douruanliang.fragmentlazyapp.domain.IpInfo;
import top.douruanliang.fragmentlazyapp.net.LoadTaskCallBack;
import top.douruanliang.fragmentlazyapp.net.NetTask;


/**
 * 任务实现类
 */
public class IpInfoTask implements NetTask<String> {

    private static IpInfoTask INSTANCE = null;

    private static final String HOST = "http://ip.taobao.com/service/getIpInfo.php";

    private LoadTaskCallBack mLoadTaskCallBack;

    private IpInfoTask(){}

    public static IpInfoTask getInstance(){
        if(INSTANCE == null){
            INSTANCE = new IpInfoTask();
        }

        return INSTANCE;
    }
    @Override
    public void execute(String ip, final LoadTaskCallBack loadTaskCallBack) {

        RequestParams requestParams = new RequestParams();

        requestParams.addFormDataPart("ip",ip);

        HttpRequest.post(HOST,requestParams,new BaseHttpRequestCallback<IpInfo>(){
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();

                loadTaskCallBack.onFinish();
            }

            @Override
            protected void onSuccess(IpInfo ipInfo) {
                super.onSuccess(ipInfo);

                loadTaskCallBack.onSuccess(ipInfo);
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);

                loadTaskCallBack.onFailed();
            }
        });

    }
}
