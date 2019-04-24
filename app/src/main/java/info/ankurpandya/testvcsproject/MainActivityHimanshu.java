package info.ankurpandya.testvcsproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivityHimanshu extends AppCompatActivity {
    TextView textView;
    Button btnSave, btnReload;
    EditText etusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_himanshu);

        DataHelper.init(MainActivityHimanshu.this);

        Button storeinformation = (Button) findViewById(R.id.storeinformation);
        Button showinformation = (Button) findViewById(R.id.showinformation);
        textView = (TextView) findViewById(R.id.txtPrefs);
        etusername = (EditText) findViewById(R.id.input_username);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnReload = (Button) findViewById(R.id.btnReload);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.storeinformation:
                        Intent intent = new Intent(MainActivityHimanshu.this, PrefActivityHimanshu.class);
                        startActivity(intent);
                        break;
                    case R.id.showinformation:
                        displaySharedPreferences();
                        break;
                    default:
                        break;
                }
            }
        };
        storeinformation.setOnClickListener(listener);
        showinformation.setOnClickListener(listener);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valueGivenByUser = etusername.getText().toString();
                DataHelper.saveString("username", valueGivenByUser);
            }
        });

        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valueSavedInPref = DataHelper.getString("username");
                etusername.setText(valueSavedInPref);
            }
        });
    }


    private void displaySharedPreferences() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivityHimanshu.this);
        String username = prefs.getString("username", "Default UserName");
        String passw = prefs.getString("password", "Default Password");
        boolean checkBox = prefs.getBoolean("checkBox", false);
        String listPrefs = prefs.getString("listpref", "Default list prefs");


        StringBuilder builder = new StringBuilder();
        builder.append("Username: " + username + "\n");
        builder.append("Password: " + passw + "\n");
        builder.append("Keep me logged in: " + String.valueOf(checkBox) + "\n");
        builder.append("List preference: " + listPrefs);
        textView.setText(builder.toString());

    }


}