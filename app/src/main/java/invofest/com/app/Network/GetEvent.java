package invofest.com.app.Network;

import invofest.com.app.Converter.BaseListResponse;
import invofest.com.app.Model.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetEvent {


    @GET("peserta/talkshow")
    Call<BaseListResponse<User>> getTalkshow();

    @GET("peserta/seminar")
    Call<BaseListResponse<User>> getSeminar();

    @GET("peserta/workshop")
    Call<BaseListResponse<User>> getWorkshop();

}
