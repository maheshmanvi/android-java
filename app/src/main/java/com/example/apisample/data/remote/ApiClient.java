package com.example.apisample.data.remote;

import androidx.annotation.NonNull;

import com.example.apisample.BuildConfig;
import com.example.apisample.utils.Constants;




import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public final class ApiClient {

    private static  final long CONNECT_TIMEOUT_SECONDS = 30L;
    private static  final long READ_TIMEOUT_SECONDS = 30L;
    private static  final long WRITE_TIMEOUT_SECONDS = 30L;
    private static volatile ApiService apiService;

    private ApiClient() {
        throw new IllegalStateException("Utility class");
    }


    // Why Double-Checked locking?
    // Thread-safe

    // We have: Timeouts, Singleton Retrofit & ApiService.
    // Debug logging only, no logs in Release builds.

    @NonNull
    public static ApiService getApiService(){
        if(apiService == null){
            synchronized (ApiClient.class){
                if(apiService == null){
                    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                    loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
                    OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS).readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS).writeTimeout(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS).addInterceptor(loggingInterceptor).build();
                    Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
                    apiService = retrofit.create(ApiService.class);
                }
            }
        }
        return apiService;
    }
}
