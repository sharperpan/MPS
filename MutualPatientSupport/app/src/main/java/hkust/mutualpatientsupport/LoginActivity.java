package hkust.mutualpatientsupport;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//Add the following imports to your libraries
import com.loopj.android.http.*;

import android.content.Context;

import org.json.*;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.protocol.BasicHttpContext;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.protocol.SyncBasicHttpContext;

import com.google.gson.*;

import java.io.UnsupportedEncodingException;

public class LoginActivity extends AppCompatActivity {
    private Button button_login = null;
    private Button button_register = null;
    private static AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button_login = (Button) findViewById(R.id.button_login);
        button_login.setOnClickListener(new LoginActivity.loginButtonListener());
        button_register = (Button) findViewById(R.id.button_register);
        button_register.setOnClickListener(new LoginActivity.registerButtonListener());
    }

    class loginButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            // need to check id and pw
            RequestParams params = new RequestParams();
            try {
                JSONObject jsonParams = new JSONObject();
                jsonParams.put("Email", "222@mail.com"); //Pick username from user control
                //Pick password from user control and encypte by md5 before sending to server side
                jsonParams.put("Password", "123");
                //Content type is mandatory otherwise format error would be catched at server side
                StringEntity se = new StringEntity(jsonParams.toString(), ContentType.APPLICATION_JSON);
                System.out.println("start to connect");
                client.removeAllHeaders();
                client.addHeader("Content-Type", "application/json");
                client.post(getApplicationContext(), "http://mspcognix.azurewebsites.net/api/Common/Login", se, "application/json", new AsyncHttpResponseHandler(
                ) {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String responseMessage = new String(responseBody).trim();
                        //you can get any items from the message for storing cookie
                        System.out.println(responseMessage);
                        JsonObject jsobj = new JsonParser().parse(responseMessage).getAsJsonObject();
                        String CID = jsobj.get("CID").getAsString();
                        System.out.println(CID);
                        if (CID.equals("-1")) {
                            //no record
                            System.out.println("No record of this user");
                            return;
                        }
                        if (CID.equals("-2")) {
                            //password is wrong
                            System.out.println("Password is wrong");
                            return;
                        }
                        if (CID.equals("999")) {
                            //server error or internal exception
                            System.out.println("Server error");
                            return;
                        }
                        System.out.println("it is workable");
                        //To Do....Cookies,Redirect

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable
                            throwable) {
                        //A message can used to alert end user here.
                        System.out.println("fail");
                        System.out.println(new String(responseBody));
                        System.out.println("throwable");
                        System.out.println(throwable.getMessage());
                    }
                });

            } catch (JSONException jex) {
                jex.printStackTrace();
            }
        }
    }

    class registerButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            // need to check id and pw
            //System.out.println("fuck ex");
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, RegisterActivity.class);
            LoginActivity.this.startActivity(intent);
        }
    }
}
