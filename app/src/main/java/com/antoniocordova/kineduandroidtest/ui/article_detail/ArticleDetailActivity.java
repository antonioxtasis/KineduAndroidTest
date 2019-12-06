package com.antoniocordova.kineduandroidtest.ui.article_detail;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.antoniocordova.kineduandroidtest.R;
import com.antoniocordova.kineduandroidtest.db.models.Article;
import com.antoniocordova.kineduandroidtest.network.ArticleDetailResponse;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;

import java.net.UnknownHostException;

public class ArticleDetailActivity extends AppCompatActivity {

    ArticleDetailPresenter presenter;

    private WebView webViewBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setTitle("");
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setDisplayShowHomeEnabled(true);
        }

        // findViews
        ImageView img = findViewById(R.id.img_article_thumbnail);
        TextView tvName = findViewById(R.id.tv_article_name);
        webViewBody = findViewById(R.id.webview_article_body);

        setupMVP();

        // Receive data
        if(getIntent().hasExtra("article")){
            Article article = (Article) getIntent().getSerializableExtra("article");

            // Render
            if (article != null) {
                Glide.with(this).load(article.getPicture()).into(img);
                tvName.setText(article.getName());
                presenter.getArticleDetail(article.getId());
            }
        }
    }

    private void setupMVP() {
        // MVP Architectural Pattern logic
        presenter = new ArticleDetailPresenterImpl(new ArticleDetailPresenterImpl.IViewEvents() {
            @Override
            public void onGetArticleDetail(ArticleDetailResponse response) {
                loadArticleDetailData(response.getData().getArticle());
            }

            @Override
            public void onError(Throwable e) {
                // Validate and manage diferent errors
                String errorMessage = getResources().getString(R.string.error_retrieving_data_from_server);
                if(e instanceof UnknownHostException){
                    errorMessage = getResources().getString(R.string.error_no_internet);
                }
                Toast.makeText(ArticleDetailActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadArticleDetailData(Article article) {
        String htmlContent = article.getBody();
        if(htmlContent!=null){
            // Load html data in webview
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading Data...");
            progressDialog.setCancelable(false);

            webViewBody.requestFocus();
            webViewBody.getSettings().setLightTouchEnabled(true);
            //webViewBody.getSettings().setJavaScriptEnabled(true);
            webViewBody.getSettings().setGeolocationEnabled(true);
            webViewBody.setSoundEffectsEnabled(true);
            webViewBody.loadData(htmlContent, "text/html", "UTF-8");
            webViewBody.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress) {
                    if (progress < 100) {
                        progressDialog.show();
                    }
                    if (progress == 100) {
                        new Handler().postDelayed(() -> progressDialog.dismiss(), 500);
                    }
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSwipeRight(this);
    }
}
