package cn.hudp.androiddevartnote.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by HuDP on 2017/2/9.
 */

public interface API {
    @GET("movie/top250")
    Call<DoubanMovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);

    @GET("movie/top250")
    Observable<DoubanMovieEntity> getTopMovie2(@Query("start") int start, @Query("count") int count);
}
