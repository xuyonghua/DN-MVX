package com.dongnaoedu.dnplayer.common.data;

import com.dongnaoedu.dnplayer.common.data.entity.ListData;
import com.dongnaoedu.dnplayer.common.data.entity.MusicInfo;
import com.dongnaoedu.dnplayer.common.data.entity.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("dongnao/music/list")
    Observable<BaseResponse<ListData<MusicInfo>>> getMusics
            (@Query("pageSize") int pageSize, @Query("pageNum") int pageNum);

    @GET("dongnao/user/info")
    Observable<BaseResponse<UserInfo>> getUserInfo(@Query("userId") String userId);

}
