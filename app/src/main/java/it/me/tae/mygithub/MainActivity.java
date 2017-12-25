package it.me.tae.mygithub;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String GITHUB_BASE_URL = "https://api.github.com/";

    private View rootLayout;
    private TextView username;
    private ImageView image;
    private TextView name;
    private TextView url;
    private TextView followers;
    private TextView following;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout = findViewById(R.id.rootLayout);
        username = (TextView) findViewById(R.id.github_username);
        image = (ImageView) findViewById(R.id.github_image);
        name = (TextView) findViewById(R.id.github_name);
        url = (TextView) findViewById(R.id.github_url);
        followers = (TextView) findViewById(R.id.github_followers);
        following = (TextView) findViewById(R.id.github_following);
        progressBar = (ProgressBar) findViewById(R.id.loading_progress_bar);
    }

    private void clearView() {
        image.setImageResource(0);
        name.setText("");
        url.setText("");
        followers.setText("");
        following.setText("");
    }

    private void showSnackbarText(String msg) {
        Snackbar.make(rootLayout, msg, Snackbar.LENGTH_LONG).show();
    }

    private void showSnackbarError(String msg) {
        Snackbar snackbar = Snackbar.make(rootLayout, msg, Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.YELLOW);

        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.RED);
        snackbar.show();
    }

}
