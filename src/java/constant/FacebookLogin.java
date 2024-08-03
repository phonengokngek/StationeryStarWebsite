
package constant;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import constant.Constant;
import model.FacebookAccount;
import model.GoogleAccount;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;


public class FacebookLogin {
    public static String getToken(String code) throws ClientProtocolException, IOException {
        String response = Request.Post(Constant.FACEBOOK_LINK_GET_TOKEN)
                .bodyForm(Form.form()
       .add("client_id", Constant.FACEBOOK_CLIENT_ID)
                        .add("client_secret", Constant.FACEBOOK_CLIENT_SECRET)
                        .add("redirect_uri", Constant.FACEBOOK_REDIRECT_URI)
                        .add("code", code)
                        .build()
                )
                .execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }
    
    public static FacebookAccount getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constant.FACEBOOK_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        FacebookAccount googlePojo = new Gson().fromJson(response, FacebookAccount.class);
        return googlePojo;
    }
}
