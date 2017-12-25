package it.me.tae.mygithub.ui.main;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.RequestParams;

import java.util.Timer;
import java.util.TimerTask;

import it.me.tae.mygithub.base.BaseMvpPresenter;
import it.me.tae.mygithub.constant.Constant;
import it.me.tae.mygithub.github.Github;
import it.me.tae.mygithub.model.GithubModel;
import it.me.tae.mygithub.restapi.RestClient;

/**
 * Created by nobtingtong on 25/12/2017 AD.
 */

public class MainActivityPresenter extends BaseMvpPresenter<MainActivityInterface.View>
        implements MainActivityInterface.Presenter {

    private RestClient restClient;


    public static MainActivityInterface.Presenter create(Context context) {
        return new MainActivityPresenter(context);
    }

    public MainActivityPresenter(Context context) {
        this.restClient = new RestClient(context);
    }

    @Override
    public void requestGithubUser(String gitUser) {
        String url = String.format(Constant.ROOT_URL, gitUser);
        RequestParams requestParams = new RequestParams();
        restClient.get(url, requestParams, GithubModel.class, new RestClient.RestClientListener<GithubModel>() {
            @Override
            public void onSuccess(GithubModel res) {
                getView().updateView(res);
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    private Timer timer=new Timer();
    private final long DELAY = 700;

    @Override
    public void validateUserInput(String gitUser) {
        if (!gitUser.isEmpty()) {
            requestGithubUser(gitUser);
        }

    }
}
