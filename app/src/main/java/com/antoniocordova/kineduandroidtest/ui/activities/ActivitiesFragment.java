package com.antoniocordova.kineduandroidtest.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.antoniocordova.kineduandroidtest.R;
import com.antoniocordova.kineduandroidtest.app.MessageEvent;
import com.antoniocordova.kineduandroidtest.app.MyUtils;
import com.antoniocordova.kineduandroidtest.network.ActivitiesResponse;
import com.bumptech.glide.Glide;
import com.github.vivchar.rendererrecyclerviewadapter.RendererRecyclerViewAdapter;
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder;
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.UnknownHostException;
import java.util.ArrayList;

public class ActivitiesFragment extends Fragment {

    private ActivitiesPresenter presenter;

    private Activity context;

    private ArrayList<com.antoniocordova.kineduandroidtest.db.models.Activity> arrActivities = new ArrayList<>();

    private RendererRecyclerViewAdapter mRecyclerViewAdapter;
    private RecyclerView mRecyclerView;

    public ActivitiesFragment(){}

    public static Fragment newInstance() {
        return new ActivitiesFragment();
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        try {
            presenter = (ActivitiesPresenter) activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activities, container, false);
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
                    mRecyclerViewAdapter.setItems(arrActivities);
                } else {
                    // TODO - improve: implement stream
                    try {
                        ArrayList<com.antoniocordova.kineduandroidtest.db.models.Activity> result = new ArrayList<>();
                        result.clear();
                        for (com.antoniocordova.kineduandroidtest.db.models.Activity x: arrActivities) {
                            if (x.getAge() == age) {
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
        presenter = new ActivitiesPresenterImpl(new ActivitiesPresenterImpl.IViewEvents() {

            @Override
            public void onGetActivities(ActivitiesResponse response) {
                arrActivities = response.getData().getActivities();
                mRecyclerViewAdapter.setItems(arrActivities);
                mRecyclerViewAdapter.notifyDataSetChanged();
                MyUtils.SharedPrefereces.saveActivitiesArray(context, arrActivities);
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
                arrActivities = MyUtils.SharedPrefereces.getActivitiesArray(context);
                mRecyclerViewAdapter.setItems(arrActivities);
                mRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

        // Get activities list
        presenter.getActivities();
    }

    private void initRecyclerView() {
        new Handler().post(() -> {
            mRecyclerViewAdapter = new RendererRecyclerViewAdapter();
            mRecyclerView = (RecyclerView) context.findViewById(R.id.recyclerview_activities);
            mRecyclerView.setLayoutManager(new GridLayoutManager(context, 1));
            mRecyclerView.setAdapter(mRecyclerViewAdapter);
            mRecyclerViewAdapter.registerRenderer(new ViewBinder<>(
                    R.layout.item_activity,
                    com.antoniocordova.kineduandroidtest.db.models.Activity.class,
                    (model, finder, payloads) -> finder
                            .find(R.id.img_activity_thumbnail, (ViewProvider<ImageView>) imgThumbnail -> {
                                Glide.with(context).load(model.getThumbnail()).into(imgThumbnail);
                            })
                            .setText(R.id.tv_activity_name, model.getName())
                            .setText(R.id.tv_activity_purpose, model.getPurpose())
            ));
        });
    }
}
