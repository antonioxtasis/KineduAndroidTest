package com.antoniocordova.kineduandroidtest.ui.activities;

import com.antoniocordova.kineduandroidtest.network.ActivitiesResponse;
import com.antoniocordova.kineduandroidtest.network.api.ApiUtils;

public class ActivitiesPresenterImpl implements ActivitiesPresenter{

    /**
     * Communicate: Impl --> "Presenter" --> UI
     */

    public interface IViewEvents {
        void onGetActivities(ActivitiesResponse response);
        void onError(String errorMessage);
    }

    /**
     * Implementer
     */

    private IViewEvents view;

    ActivitiesPresenterImpl(IViewEvents iv) {
        this.view = iv;
    }

    /**
     * Methods
     */

    @Override
    public void getActivities() {
        ApiUtils.getActivities(new ApiUtils.BasicListener() {
            @Override
            public void onSuccess(Object resp) {
                ActivitiesResponse response = (ActivitiesResponse) resp;
                view.onGetActivities(response);
            }

            @Override
            public void onError(String errorMessage) {
                view.onError(errorMessage);
            }
        });
    }
}
