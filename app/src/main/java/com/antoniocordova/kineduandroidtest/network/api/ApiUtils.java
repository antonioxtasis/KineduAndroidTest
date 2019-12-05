package aegis.io.network.api;

import android.util.Log;

import aegis.io.db.model.Batch;
import aegis.io.db.model.User;
import aegis.io.network.AcceptOrRejectRequestInboxResponse;
import aegis.io.network.BaseRequest;
import aegis.io.network.BatchGenesisRequest;
import aegis.io.network.BatchGenesisResponse;
import aegis.io.network.CurrentTimestampResponse;
import aegis.io.network.LoginResponse;
import aegis.io.network.ModulesRequestsResponse;
import aegis.io.network.ModulesResponse;
import aegis.io.network.PartnersRequestsResponse;
import aegis.io.network.ProfileResponse;
import aegis.io.network.SaveBatchFormRequest;
import aegis.io.network.SaveBatchFormResponse;
import aegis.io.network.SignupRequest;
import aegis.io.network.SignupResponse;
import aegis.io.network.UploadFileResponse;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

public class ApiUtils {

    private ApiUtils() { }

    private static APIService getAPIService() {
        return RetrofitClient.getClient().create(APIService.class);
    }

    public interface BasicListener {
        void onSuccess(Object response);
        void onError(String errorMessage);
    }

    /**************************************
     *
     * Métodos a la API
     *
     **************************************/

    public static void login(final String username, final String password, BasicListener callback) {
        getAPIService()
                .login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("login", "onSubscribe");
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        Log.d("login", "onNext");
                        callback.onSuccess(loginResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("login", "onError");
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("login", "onComplete");
                    }
                });

//        Call<LoginResponse> call = getAPIService().login(request);
//        call.enqueue(new Callback<LoginResponse>() {
//            @Override
//            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                listener.onSuccess("Success");
//            }
//
//            @Override
//            public void onFailure(Call<LoginResponse> call, Throwable t) {
//                listener.onError();
//            }
//        });
    }

    public static void signup(final SignupRequest request, BasicListener callback) {
        try{
            getAPIService()
                    .signup(request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<SignupResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.d("signup", "onSubscribe");
                        }

                        @Override
                        public void onNext(SignupResponse response) {
                            Log.d("signup", "onNext");
                            callback.onSuccess(response);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("signup", "onError");
                            callback.onError(e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            Log.d("signup", "onComplete");
                        }
                    });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateProfile(User user, BasicListener callback) {
        try{
            getAPIService()
                    .updateProfile(user.getUserId(), user)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ProfileResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.d("updateProfile", "onSubscribe");
                        }

                        @Override
                        public void onNext(ProfileResponse response) {
                            Log.d("updateProfile", "onNext");
                            callback.onSuccess(response);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("updateProfile", "onError");
                            callback.onError(e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            Log.d("updateProfile", "onComplete");
                        }
                    });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void getModules(String userId, BasicListener callback) {
        //userId = "5cd5361009203a18b6760461";
        getAPIService()
                .getModules(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModulesResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("getModules", "onSubscribe");
                    }

                    @Override
                    public void onNext(ModulesResponse modulesResponse) {
                        Log.d("getModules", "onNext");
                        callback.onSuccess(modulesResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("getModules", "onError");
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("getModules", "onComplete");
                    }
                });
    }

    public static void getProfile(String userId, BasicListener callback) {
        getAPIService()
                .getProfile(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProfileResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("getProfile", "onSubscribe");
                    }

                    @Override
                    public void onNext(ProfileResponse profileResponse) {
                        Log.d("getProfile", "onNext");
                        callback.onSuccess(profileResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("getProfile", "onError");
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("getProfile", "onComplete");
                    }
                });
    }

    public static void createBatchGenesis(final BatchGenesisRequest request, BasicListener callback) {
        getAPIService()
                .createBatchGenesis(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BatchGenesisResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("createBatchGenesis", "onSubscribe");
                    }

                    @Override
                    public void onNext(BatchGenesisResponse response) {
                        Log.d("createBatchGenesis", "onNext");
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("createBatchGenesis", "onError");
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("createBatchGenesis", "onComplete");
                    }
                });
    }

    public static void saveBatchForm(final SaveBatchFormRequest request, BasicListener callback) {
        getAPIService()
                .saveBatchForm(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SaveBatchFormResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("saveBatchForm", "onSubscribe");
                    }

                    @Override
                    public void onNext(SaveBatchFormResponse response) {
                        Log.d("saveBatchForm", "onNext");
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("saveBatchForm", "onError");
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("saveBatchForm", "onComplete");
                    }
                });
    }

    public static void getCurrentUtcTimestamp(BasicListener callback) {
        getAPIService()
                .getCurrentUtcTimestamp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CurrentTimestampResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("getCurrentUtcTimestamp", "onSubscribe");
                    }

                    @Override
                    public void onNext(CurrentTimestampResponse response) {
                        Log.d("getCurrentUtcTimestamp", "onNext");
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("getCurrentUtcTimestamp", "onError");
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("getCurrentUtcTimestamp", "onComplete");
                    }
                });
    }

    public static void getPartnersRequests(final BaseRequest request, BasicListener callback) {
        getAPIService()
                .getPartnersRequests(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PartnersRequestsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("getPartnersRequests", "onSubscribe");
                    }

                    @Override
                    public void onNext(PartnersRequestsResponse modulesResponse) {
                        Log.d("getPartnersRequests", "onNext");
                        callback.onSuccess(modulesResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("getPartnersRequests", "onError");
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("getPartnersRequests", "onComplete");
                    }
                });
    }

    public static void acceptOrRejectPartnerRequest(final BaseRequest request, BasicListener callback) {
        getAPIService()
                .acceptOrRejectPartnerRequest(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AcceptOrRejectRequestInboxResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("acceptOrRejectPartnerRe", "onSubscribe");
                    }

                    @Override
                    public void onNext(AcceptOrRejectRequestInboxResponse modulesResponse) {
                        Log.d("acceptOrRejectPartnerRe", "onNext");
                        callback.onSuccess(modulesResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("acceptOrRejectPartnerRe", "onError");
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("acceptOrRejectPartnerRe", "onComplete");
                    }
                });
    }

    public static void getModulesRequests(final BaseRequest request, BasicListener callback) {
        getAPIService()
                .getModulesRequests(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModulesRequestsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("getModulesRequests", "onSubscribe");
                    }

                    @Override
                    public void onNext(ModulesRequestsResponse modulesResponse) {
                        Log.d("getModulesRequests", "onNext");
                        callback.onSuccess(modulesResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("getModulesRequests", "onError");
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("getModulesRequests", "onComplete");
                    }
                });
    }

    public static void markModuleAsSeen(final BaseRequest request, BasicListener callback) {
        getAPIService()
                .markModuleAsSeen(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AcceptOrRejectRequestInboxResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("acceptOrRejectModuleRe", "onSubscribe");
                    }

                    @Override
                    public void onNext(AcceptOrRejectRequestInboxResponse modulesResponse) {
                        Log.d("acceptOrRejectModuleRe", "onNext");
                        callback.onSuccess(modulesResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("acceptOrRejectModuleRe", "onError");
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("acceptOrRejectModuleRe", "onComplete");
                    }
                });
    }

    public static void uploadFile(MultipartBody.Part file, String userId, BasicListener callback) {
        getAPIService()
                .uploadFile(file, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UploadFileResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("uploadFile", "onSubscribe");
                    }

                    @Override
                    public void onNext(UploadFileResponse response) {
                        Log.d("uploadFile", "onNext");
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("uploadFile", "onError");
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("uploadFile", "onComplete");
                    }
                });
    }
}
