package it.me.tae.mygithub.ui.main;

import java.util.List;

import it.me.tae.mygithub.base.BaseMvpInterface;
import it.me.tae.mygithub.model.GithubModel;

/**
 * Created by nobtingtong on 25/12/2017 AD.
 */

public interface MainActivityInterface {

    public interface View extends BaseMvpInterface.View{
        void initListener();
        void updateView(GithubModel githubModel);
    }

    public interface Presenter extends  BaseMvpInterface.Presenter<MainActivityInterface.View>{
        void requestGithubUser(String gitUser);
        void validateUserInput(String gitUser);
    }
}
