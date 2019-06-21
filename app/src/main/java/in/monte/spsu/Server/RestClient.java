package in.monte.spsu.Server;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Monte on 1/27/2018.
 */

public class RestClient {
    private static final String BASE_URL = "http://192.168.137.1/Notice_spsu/";
    private static Retrofit retrofit = null;


    public static Retrofit getRestClient(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
