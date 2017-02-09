package demo.android.retrofitdemopost;

/**
 * Created by ln-149 on 8/2/17.
 */

public class ApiUtils {


    private ApiUtils() {}

    public static final String BASE_URL = "http://bi.way.com/responsive/mobiwebservice/api/";

    public static ApiService getApiService() {

        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
