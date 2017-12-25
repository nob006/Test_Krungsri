package it.me.tae.mygithub;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import it.me.tae.mygithub.base.BaseMvpActivity;
import it.me.tae.mygithub.model.GithubModel;
import it.me.tae.mygithub.ui.main.MainActivityInterface;
import it.me.tae.mygithub.ui.main.MainActivityPresenter;

public class MainActivity extends BaseMvpActivity<MainActivityInterface.Presenter>
        implements MainActivityInterface.View{



    private View rootLayout;
    private EditText username;
    private ImageView image;
    private TextView name;
    private TextView url;
    private TextView followers;
    private TextView following;
    private ProgressBar progressBar;


    @Override
    public MainActivityInterface.Presenter createPresenter() {
        return MainActivityPresenter.create(this);
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    public void bindView() {
        rootLayout = findViewById(R.id.rootLayout);
        username = (EditText) findViewById(R.id.github_username);
        image = (ImageView) findViewById(R.id.github_image);
        name = (TextView) findViewById(R.id.github_name);
        url = (TextView) findViewById(R.id.github_url);
        followers = (TextView) findViewById(R.id.github_followers);
        following = (TextView) findViewById(R.id.github_following);
        progressBar = (ProgressBar) findViewById(R.id.loading_progress_bar);
    }

    @Override
    public void setupInstance() {

    }

    @Override
    public void setupView() {
        initListener();
    }

    @Override
    public void initialize() {

    }

    @Override
    public void initListener() {
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getPresenter().validateUserInput(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void updateView(GithubModel githubModel) {
        name.setText(githubModel.getName());
        url.setText(githubModel.getUrl());

        Picasso.with(this).load(githubModel.getAvatar_url()).into(image);
    }
}
