package demo.android.retrofitdemopost;

import demo.android.retrofitdemopost.api.Data;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ln-149 on 8/2/17.
 */

public interface ApiService {

    @POST("get_orders.php")
    @FormUrlEncoded
    Call<Data> savePost(@Field("status") String status,
                        @Field("page") String body,
                        @Field("userId") long userId);
}
