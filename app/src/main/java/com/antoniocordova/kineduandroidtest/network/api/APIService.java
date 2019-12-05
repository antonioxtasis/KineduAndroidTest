package aegis.io.network.api;

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
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface APIService {

    @FormUrlEncoded
    @POST("user/auth/")
    Observable<LoginResponse> login(@Field("email") String email, @Field("password") String password);

    @POST("user/")
    Observable<SignupResponse> signup(@Body SignupRequest bodyParams);

    @GET("user/{userId}")
    Observable<ProfileResponse> getProfile(@Path("userId") String userId);

    @PUT("user/{userId}")
    Observable<ProfileResponse> updateProfile(@Path("userId") String userId, @Body User bodyParams);

    @GET("module/permission/{userId}")
    Observable<ModulesResponse> getModules(@Path("userId") String userId);

    @POST("batch/genesis")
    Observable<BatchGenesisResponse> createBatchGenesis(@Body BatchGenesisRequest bodyParams);

    @POST("formfields/data")
    Observable<SaveBatchFormResponse> saveBatchForm(@Body SaveBatchFormRequest bodyParams);

    @GET("utils/UnixTimestamp")
    Observable<CurrentTimestampResponse> getCurrentUtcTimestamp();

    @Multipart
    @POST("aws/s3/upload")
    Observable<UploadFileResponse> uploadFile(@Part MultipartBody.Part aegis_file, @Part("creator_user_id") String userId);



    @POST("get_partners_requests")
    Observable<PartnersRequestsResponse> getPartnersRequests(@Body BaseRequest json);

    @POST("accept_or_reject_partner_request")
    Observable<AcceptOrRejectRequestInboxResponse> acceptOrRejectPartnerRequest(@Body BaseRequest json);

    @POST("get_modules_requests")
    Observable<ModulesRequestsResponse> getModulesRequests(@Body BaseRequest json);

    @POST("mark_module_as_seen")
    Observable<AcceptOrRejectRequestInboxResponse> markModuleAsSeen(@Body BaseRequest json);

}
