package com.antoniocordova.kineduandroidtest.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import com.antoniocordova.kineduandroidtest.network.ActivitiesResponse;
import com.bumptech.glide.Glide;
import com.github.vivchar.rendererrecyclerviewadapter.RendererRecyclerViewAdapter;
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder;
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewProvider;

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

    // Interface
    private Listener mCallback;
    public interface Listener {
        //void onModuleSelected(Module model);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (Listener) activity;
            presenter = (ActivitiesPresenter) activity;
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
        return inflater.inflate(R.layout.fragment_activities, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();

        //initSearchView();
        initRecyclerView();

        setupMVP();
    }

    private void setupMVP() {

        // MVP Architectural Pattern logic
        presenter = new ActivitiesPresenterImpl(new ActivitiesPresenterImpl.IViewEvents() {

            @Override
            public void onGetActivities(ActivitiesResponse response) {
                arrActivities = response.getData().getActivities();
                mRecyclerViewAdapter.setItems(arrActivities);
                mRecyclerViewAdapter.notifyDataSetChanged();
                Log.d("hello", "hello");
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
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
//                            .find(R.id.ll_item_module, (ViewProvider<LinearLayout>) llItemModule -> {
//                                llItemModule.setOnClickListener(v -> {
//                                    //Display batches
//                                    mCallback.onModuleSelected(model);
//                                });
//                            })
                            .setText(R.id.tv_activity_name, model.getName())
                            .setText(R.id.tv_activity_purpose, model.getPurpose())
            ));
        });
    }

}
