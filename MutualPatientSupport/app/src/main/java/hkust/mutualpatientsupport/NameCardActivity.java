package hkust.mutualpatientsupport;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.entity.StringEntity;

public class NameCardActivity extends AppCompatActivity {

    private static AsyncHttpClient client = new AsyncHttpClient();
    private static int CID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_card);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_name_card);
        setSupportActionBar(toolbar);
        System.out.println("come in");
        final TextView PatientName = (TextView) findViewById(R.id.cardview_txtName);

        final TextView PatientAge = (TextView) findViewById(R.id.cardview_age);

        final TextView PatientAddress = (TextView) findViewById(R.id.cardview_address);

        final TextView CaregiverName = (TextView) findViewById(R.id.cardview_txtName_caregiver);

        final TextView CaregiverPhone = (TextView) findViewById(R.id.cardview_phone_caregiver);

        final TextView CaregiverAddress = (TextView) findViewById(R.id.cardview_address_caregiver);


        final RequestParams Param = new RequestParams("ID", CID);
        Param.put("ID", CID);
        System.out.println("ready");
        client.get(getApplicationContext(), "http://mspcognix.azurewebsites.net/api/Caregiver/GetByID", Param, new AsyncHttpResponseHandler(
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
                if (CID.equals("999")) {
                    //server error or internal exception
                    System.out.println("Server error");
                    return;
                }
                CaregiverAddress.setText(jsobj.get("StreetAddress").getAsString() + jsobj.get("BuildingAddress").getAsString());
                CaregiverName.setText(jsobj.get("FirstName").getAsString() + jsobj.get("LastName").getAsString());
                CaregiverPhone.setText(jsobj.get("Phone").getAsString());

                client.get(getApplicationContext(), "http://mspcognix.azurewebsites.net/api/Patient/GetByCaregiverID", Param, new AsyncHttpResponseHandler(
                ) {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String responseMessage = new String(responseBody).trim();
                        //you can get any items from the message for storing cookie
                        System.out.println(responseMessage);
                        JsonObject jsobj = new JsonParser().parse(responseMessage).getAsJsonObject();
                        String CID = jsobj.get("PID").getAsString();
                        System.out.println(CID);
                        if (CID.equals("-1")) {
                            //no record
                            System.out.println("No record of this user");
                            return;
                        }
                        if (CID.equals("999")) {
                            //server error or internal exception
                            System.out.println("Server error");
                            return;
                        }
                        PatientAddress.setText(jsobj.get("StreetAddress").getAsString() + jsobj.get("BuildingAddress").getAsString());
                        PatientName.setText(jsobj.get("FirstName").getAsString() + jsobj.get("LastName").getAsString());
                        PatientAge.setText(jsobj.get("DateOfBirth").getAsString());
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

                //end of first succ
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
    }

}
