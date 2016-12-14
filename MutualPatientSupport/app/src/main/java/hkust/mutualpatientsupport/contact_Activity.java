package hkust.mutualpatientsupport;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class contact_Activity extends AppCompatActivity {

    private static AsyncHttpClient client = new AsyncHttpClient();
    private static int CID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_contact);
        setSupportActionBar(toolbar);

        final TextView PatientName = (TextView) findViewById(R.id.cardview_txtName_contact);

        final TextView PatientAge = (TextView) findViewById(R.id.cardview_age_contact);

        final TextView PatientAddress = (TextView) findViewById(R.id.cardview_address_contact);

        final RequestParams Param = new RequestParams("ID", CID);
        Param.put("ID", CID);
        System.out.println("ready");
        client.get(getApplicationContext(), "http://mspcognix.azurewebsites.net/api/Patient/GetByCaregiverID", Param, new AsyncHttpResponseHandler(
        ) {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responseMessage = new String(responseBody).trim();
                //you can get any items from the message for storing cookie
                System.out.println(responseMessage);
                JsonObject jsobj = new JsonParser().parse(responseMessage).getAsJsonObject();
                String PID = jsobj.get("PID").getAsString();
                System.out.println(CID);
                if (PID.equals("-1")) {
                    //no record
                    System.out.println("No record of this user");
                    return;
                }
                if (PID.equals("999")) {
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


        findViewById(R.id.button_cardview_Callcaregiver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "852-52249407";
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            }
        });

        findViewById(R.id.button_cardview_CallHospital).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "852-68196690";
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            }
        });
    }

}
