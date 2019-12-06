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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.antoniocordova.kineduandroidtest.R;
import com.antoniocordova.kineduandroidtest.db.models.Article;
import com.antoniocordova.kineduandroidtest.network.ArticlesResponse;
import com.antoniocordova.kineduandroidtest.ui.article_detail.ArticleDetailActivity;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.github.vivchar.rendererrecyclerviewadapter.RendererRecyclerViewAdapter;
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder;
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewProvider;

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

    // Interface
    private ArticlesFragment.Listener mCallback;
    public interface Listener {
        void onArticleSelected(Article model);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (Listener) activity;
            presenter = (ArticlesPresenter) activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
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

    private void setupMVP() {

        // MVP Architectural Pattern logic
        presenter = new ArticlesPresenterImpl(new ArticlesPresenterImpl.IViewEvents() {
            @Override
            public void onGetArticles(ArticlesResponse response) {
                arrArticles = response.getData().getArticles();
                mRecyclerViewAdapter.setItems(arrArticles);
                mRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errorMessage) {

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
                                    //mCallback.onArticleSelected(model);
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
