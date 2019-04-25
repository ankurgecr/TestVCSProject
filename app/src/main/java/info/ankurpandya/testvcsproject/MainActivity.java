package info.ankurpandya.testvcsproject;

import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import info.ankurpandya.testvcsproject.ankur.TestAnkurActivity;

public class MainActivity extends AppCompatActivity {

    Button btn_test_ankur, btn_test_govind, btn_test_sapna, btn_test_himanshu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_test_ankur = findViewById(R.id.btn_test_ankur);
        btn_test_govind = findViewById(R.id.btn_test_govind);
        btn_test_sapna = findViewById(R.id.btn_test_sapna);
        btn_test_himanshu = findViewById(R.id.btn_test_himanshu);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hello Twist", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btn_test_ankur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAnkurTest();
            }
        });

        btn_test_govind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGovindTest();
            }
        });

        btn_test_sapna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSapnaTest();
            }
        });

        btn_test_himanshu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHimanshuTest();
            }
        });
    }

    private void openAnkurTest() {
        Intent intent = new Intent(MainActivity.this, TestAnkurActivity.class);
        startActivity(intent);
    }

    private void openGovindTest() {
        Intent intent = new Intent(MainActivity.this, ActivityGovind.class);
        startActivity(intent);
    }

    private void openSapnaTest() {
        Intent intent = new Intent(MainActivity.this, SapnaMainActivity.class);
        startActivity(intent);
    }

    private void openHimanshuTest() {
        boolean shouldOpenFingerPrint = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            if (fingerprintManager.isHardwareDetected()) {
                shouldOpenFingerPrint = true;
            }
        }

        if (shouldOpenFingerPrint) {
            Intent in = new Intent(MainActivity.this, FingerPrintActHimanshu.class);
            startActivity(in);
        } else {
            Toast.makeText(this, "Your Device does not have a Fingerprint Sensor", Toast.LENGTH_SHORT).show();
            Intent in = new Intent(MainActivity.this, MainActivityHimanshu.class);
            startActivity(in);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
