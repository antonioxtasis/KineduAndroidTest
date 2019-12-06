package com.antoniocordova.kineduandroidtest.ui.articles;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.antoniocordova.kineduandroidtest.R;
import com.antoniocordova.kineduandroidtest.app.MessageEvent;
import com.antoniocordova.kineduandroidtest.app.MyUtils;
import com.antoniocordova.kineduandroidtest.db.models.Article;
import com.antoniocordova.kineduandroidtest.network.ArticlesResponse;
import com.antoniocordova.kineduandroidtest.ui.article_detail.ArticleDetailActivity;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.github.vivchar.rendererrecyclerviewadapter.RendererRecyclerViewAdapter;
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder;
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.UnknownHostException;
import java.util.ArrayList;

public class ArticlesFragment extends Fragment {

    private ArticlesPresenter presenter;
    private Activity context;

    private ArrayList<Article> arrArticles = new ArrayList<>();
    private RendererRecyclerViewAdapter mRecyclerViewAdapter;
    private RecyclerView mRecyclerView;

    public ArticlesFragment(){}

    public static Fragment newInstance() {
        return new ArticlesFragment();
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        try {
            presenter = (ArticlesPresenter) activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_articles, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();

        initRecyclerView();
        setupMVP();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAgeFilteredMessageEvent(MessageEvent event) {
        switch (event.getOption()){
            case MessageEvent.Option.AGE_FILTERED:
                // filter activities by age
                int age = event.getAgeOption();
                if(age==0){
                    mRecyclerViewAdapter.setItems(arrArticles);
                } else {
                    // TODO - improve: implement stream
                    try {
                        ArrayList<Article> result = new ArrayList<>();
                        result.clear();
                        for (Article x: arrArticles) {
                            if (age >= x.getMinAge() && age <= x.getMaxAge()) {
                                result.add(x);
                            }
                        }
                        mRecyclerViewAdapter.setItems(result);
                    } catch (Exception e) {}
                }
                mRecyclerViewAdapter.notifyDataSetChanged();
                break;
        }
    };

    private void setupMVP() {
        // MVP Architectural Pattern logic
        presenter = new ArticlesPresenterImpl(new ArticlesPresenterImpl.IViewEvents() {
            @Override
            public void onGetArticles(ArticlesResponse response) {
                arrArticles = response.getData().getArticles();
                mRecyclerViewAdapter.setItems(arrArticles);
                mRecyclerViewAdapter.notifyDataSetChanged();
                MyUtils.SharedPrefereces.saveArticlesArray(context, arrArticles);
            }

            @Override
            public void onError(Throwable e) {
                // Validate and manage diferent errors
                String errorMessage = context.getResources().getString(R.string.error_retrieving_data_from_server);
                if(e instanceof UnknownHostException){
                    errorMessage = context.getResources().getString(R.string.error_no_internet);
                }

                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();

                // If error: retrieve data from SharedPreferences
                arrArticles = MyUtils.SharedPrefereces.getArticlesArray(context);
                mRecyclerViewAdapter.setItems(arrArticles);
                mRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

        // Get articles list
        presenter.getArticles();
    }

    private void initRecyclerView() {
        new Handler().post(() -> {
            mRecyclerViewAdapter = new RendererRecyclerViewAdapter();
            mRecyclerView = (RecyclerView) context.findViewById(R.id.recyclerview_articles);
            mRecyclerView.setLayoutManager(new GridLayoutManager(context, 1));
            mRecyclerView.setAdapter(mRecyclerViewAdapter);
            mRecyclerViewAdapter.registerRenderer(new ViewBinder<>(
                    R.layout.item_article,
                    Article.class,
                    (model, finder, payloads) -> finder
                            .find(R.id.img_article_thumbnail, (ViewProvider<ImageView>) imgThumbnail -> {
                                Glide.with(context).load(model.getPicture()).into(imgThumbnail);
                            })
                            .find(R.id.ll_container_article, (ViewProvider<LinearLayout>) llItem -> {
                                llItem.setOnClickListener(v -> {
                                    context.startActivity(new Intent(context, ArticleDetailActivity.class)
                                            .putExtra("article", model)
                                    );
                                    Animatoo.animateSwipeLeft(context);
                                });
                            })
                            .setText(R.id.tv_article_name, model.getName())
                            .setText(R.id.tv_article_short_description, model.getShortDescription())
            ));
        });
    }
}
