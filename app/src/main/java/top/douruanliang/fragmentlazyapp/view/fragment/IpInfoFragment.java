package top.douruanliang.fragmentlazyapp.view.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.douruanliang.fragmentlazyapp.R;
import top.douruanliang.fragmentlazyapp.domain.IpData;
import top.douruanliang.fragmentlazyapp.domain.IpInfo;
import top.douruanliang.fragmentlazyapp.net.IpInfoContract;

public class IpInfoFragment extends Fragment implements IpInfoContract.View {


    @BindView(R.id.textView_country)
    TextView textViewCountry;
    @BindView(R.id.textView_city)
    TextView textViewCity;
    @BindView(R.id.textView_area)
    TextView textViewArea;
    @BindView(R.id.button)
    Button button;

    private IpInfoContract.Presenter mPresenter;

    private Dialog mDialog;


    public static IpInfoFragment newInstance(){
        return new IpInfoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_ipinfo, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mDialog = new ProgressDialog(getActivity());
        mDialog.setTitle("获取数据中……");
    }

    @Override
    public void setIpInfo(IpInfo ipInfo) {
        if(ipInfo!=null && ipInfo.getIpData()!=null){
            IpData ipData = ipInfo.getIpData();

            textViewCountry.setText(ipData.getCountry());
              textViewCity.setText(ipData.getCity());
              textViewArea.setText(ipData.getArea());

        }
    }

    @Override
    public void showLoading() {
       mDialog.show();
    }

    @Override
    public void hideLoading() {
       mDialog.hide();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(IpInfoContract.Presenter presenter) {
        mPresenter= presenter;
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity().getApplicationContext(), "网络出错", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        mPresenter.getIpInfo("39.155.184.147");
    }
}
