package com.lbt.community_stu.community_stu.prodiver;

import com.alibaba.fastjson.JSON;
import com.lbt.community_stu.community_stu.dto.AccessTokenDTO;
import com.lbt.community_stu.community_stu.dto.GitHubUser;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;


/**
 * Created by lbt on 2019/9/18 15:38
 */
@Component
@Slf4j
public class GitHubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        }catch (Exception e){
            log.error("getAccessToken error,{}",accessTokenDTO,e);
        }
        return null;
    }

    public GitHubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            System.out.println(string);
            GitHubUser user = JSON.parseObject(string,GitHubUser.class);
            return user;
        }catch (Exception e){
            log.error("getUser error,{}",accessToken,e);
        }
        return null;
    }

}
