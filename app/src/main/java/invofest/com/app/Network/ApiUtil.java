package invofest.com.app.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtil {
    public static final String ENDPOINT = "https://invofest.com/";
    private static final String BASE_URL = "https://invofest.com/api/";
    private static Retrofit retrofit;

    private static Retrofit getApiClient(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static GetEvent getEvent(){
        return getApiClient().create(GetEvent.class);
    }

    public static PostEvent postEvent(){
        return getApiClient().create(PostEvent.class);
    }
}
