package aegis.io.network.api;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import aegis.io.app.MyApplication;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String TAG = RetrofitClient.class.getSimpleName();
    private static final String API_BASE_URL = "https://aegischain.herokuapp.com/api/"; //"http://demo8322189.mockable.io/"; // "https://aegischain.io/api/1.0/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder builder = original.newBuilder();

                    if(MyApplication.jwtIsAuth){
                        builder.header("Authorization", ("Bearer " + MyApplication.jwtToken));
                    }

                    Request request = builder
                            .method(original.method(), original.body())
                            .build();

                    return chain.proceed(request);
                }
            });

            //An OkHttp interceptor which logs HTTP request and response data.
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);

            OkHttpClient okHttpClient =
                    httpClient.readTimeout(120, TimeUnit.SECONDS)
                            .connectTimeout(120, TimeUnit.SECONDS).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
