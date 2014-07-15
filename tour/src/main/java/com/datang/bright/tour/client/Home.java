//package com.datang.bright.tour.client;
//
//import android.annotation.TargetApi;
//import android.os.Build;
//import android.util.Base64;
//import android.util.Log;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.lang.reflect.Type;
//
//import retrofit.RestAdapter;
//import retrofit.converter.ConversionException;
//import retrofit.converter.Converter;
//import retrofit.http.GET;
//import retrofit.http.Headers;
//import retrofit.http.Path;
//import retrofit.mime.TypedInput;
//import retrofit.mime.TypedOutput;
//
///**
// * Created by l on 14-7-10.
// */
//public class Home {
//    public static final String TAG = "HomeClient";
//
//    interface IHome {
//        @Headers({
//                "Accept: application/json",
//                "User-Agent: BrightApp",
//                "Content-Type:application/json",
//                "Content-Encoding:UTF-8"
//        })
//        @GET("/iapi/appserver/view/homeDataV391/{input}")
//        void loadHomeData(@Path("input") String inputInfo, Callback<HomeDataInfo> cb);
//
//
//    }
//
//    public static void loadHomeData(String inputInfo, Callback<HomeDataInfo> cb) {
//        RestAdapter.Builder builder = new RestAdapter.Builder();
//        builder.setConverter(new Converter() {
//            @TargetApi(Build.VERSION_CODES.FROYO)
//            @Override
//            public Object fromBody(TypedInput body, Type type) throws ConversionException {
//                if (body.length() > 0) {
//                    byte[] buf = new byte[(int) body.length()];
//                    try {
//                        body.in().read(buf);
//                        JSONObject jsonObject = new JSONObject(new String(Base64.decode(buf, 0)));
//                        Log.e(TAG, jsonObject.toString());
//                        return  new Gson().fromJson(jsonObject.getString("data"),HomeDataInfo.class);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                return body;
//            }
//
//            @Override
//            public TypedOutput toBody(Object object) {
//                return null;
//            }
//        });
//        builder.setEndpoint(Login.API_URL);
//        builder.setLogLevel(RestAdapter.LogLevel.FULL);
//        RestAdapter restAdapter = builder.build();
//        IHome lc = restAdapter.create(IHome.class);
//        lc.loadHomeData(inputInfo, cb);
//    }
//}
