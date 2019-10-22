package invofest.com.app.Network;

import invofest.com.app.Converter.BaseResponse;
import invofest.com.app.Model.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PostEvent {

    @FormUrlEncoded
    @POST("validasi_seminar")
    Call<BaseResponse<User>> absen_seminar(@Field("id_peserta") String id_peserta);

    @FormUrlEncoded
    @POST("validasi_workshop")
    Call<BaseResponse<User>> absen_workshop(@Field("id_peserta") String id_peserta);

    @FormUrlEncoded
    @POST("validasi_talkshow")
    Call<BaseResponse<User>> absen_talkshow(@Field("id_peserta") String id_peserta);

}
