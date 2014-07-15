package com.datang.bright.tour.client;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by l on 14-7-10.
 */
public class Login {

    public static final String TAG = "LoginClient";
    public static final String API_URL ="172.30.4.231:3000";

    interface ILogin {
        @Headers({
                "Accept: application/json",
                "User-Agent: Tour-BrightApp",
                "Content-Type:application/json",
                "Content-Encoding:UTF-8"
        })
        @POST("/api/sessions")
        void login(@Body User user, Callback<Result> cb);

        @Headers({
                "Accept: application/json",
                "User-Agent: Tour-BrightApp",
                "Content-Type:application/json",
                "Content-Encoding:UTF-8"
        })
        @POST("/api/registrations")
        void regist(@Body User user, Callback<Result> cb);
    }

    // http://localhost:3000/api/registrations -d
    // "{\"user\":{\"email\":\"user1@example.com\",\"password\":\"abcd.1234\",\"password_confirmation\":\"abcd.1234\"}}鈥�
    public static void regist(String id, String password, Callback<Result> cb) {
        User user = new User();
        user.user = new UserData(id, password);
        regist(user, cb);
    }

    public static void login(String id, String password, Callback<Result> cb) {
        User user = new User();
        user.user = new UserData(id, password);
        login(user, cb);
    }

    public static void regist(User user, Callback<Result> cb) {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).setLogLevel(RestAdapter.LogLevel.FULL).build();
        ILogin lc = restAdapter.create(ILogin.class);
        lc.regist(user, cb);
    }

    public static void login(User user, Callback<Result> cb) {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).setLogLevel(RestAdapter.LogLevel.FULL).build();
        ILogin lc = restAdapter.create(ILogin.class);
        lc.login(user, cb);
    }


    /**
     * "{\"user\":{\"email\":\"111@qq.com\",\"password\":\"abcd.1234\"}}"
     */
    static class User {
        UserData user;
    }

    static class UserData {
        String email;
        String password;
        String password_confirmation;

        UserData(String id, String password) {
            this.email = id;
            this.password = password;
            this.password_confirmation = password;
        }

        @Override
        public String toString() {
            return email + ":" + password;
        }
    }

    /**
     * {"success":true,"info":"Logged in","data":{"auth_token":"pAzTzsmaXFdQSyt51TUs"}}
     */
    public static class Result {
        public boolean success;
        public String info;
        AuthToken data;

        public String getToken() {
            return data.auth_token;
        }
    }

    static class AuthToken {
        String auth_token;
    }
}
