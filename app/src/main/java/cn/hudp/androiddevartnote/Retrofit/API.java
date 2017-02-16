package cn.hudp.androiddevartnote.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by HuDP on 2017/2/9.
 */

public interface API {
    @GET("top250")
    Call<DoubanMovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
}
