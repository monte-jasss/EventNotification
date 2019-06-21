package in.monte.spsu.Server;

import java.util.List;

import in.monte.spsu.Notice.NoticeData;
import in.monte.spsu.Result.ResultData;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Monte on 1/27/2018.
 */

public interface ServerAPI {

    @POST("show-notice.php")
    Call<List<NoticeData>> getNotice();

    @FormUrlEncoded
    @POST("show-result.php")
    Call<List<ResultData>> getResult(@Field("enrollment") String enrollment);

    @FormUrlEncoded
    @POST("time-table.php")
    Call<ResponseBody> getTimeTable(@Field("semester") String semester, @Field("branch") String Branch);

    @POST("semester.php")
    Call<List<String>> getSemester();

    @POST("branch.php")
    Call<List<String>> getBranch();
}
