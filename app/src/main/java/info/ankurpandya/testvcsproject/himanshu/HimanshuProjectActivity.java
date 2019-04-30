package info.ankurpandya.testvcsproject.himanshu;

import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import info.ankurpandya.testvcsproject.FingerPrintActHimanshu;
import info.ankurpandya.testvcsproject.MainActivityHimanshu;
import info.ankurpandya.testvcsproject.R;
import info.ankurpandya.testvcsproject.himanshu.Location_himanshu.LocationMainActivity;

public class HimanshuProjectActivity extends AppCompatActivity {
    Button btn_pro_1, btn_pro_2,btn_pro_3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.himanshu_project_list);
        btn_pro_1 = (Button) findViewById(R.id.btn_pro_h1);
        btn_pro_2 = (Button) findViewById(R.id.btn_pro_h2);
        btn_pro_3 = (Button) findViewById(R.id.btn_pro_h3);

        btn_pro_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHimanshuProject();
            }
        });
        btn_pro_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHimanshuProject2();
            }
        });
        btn_pro_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHimanshuProject3();
            }
        });
    }

    private void openHimanshuProject() {
        boolean shouldOpenFingerPrint = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            if (fingerprintManager.isHardwareDetected()) {
                shouldOpenFingerPrint = true;
            }
        }

        if (shouldOpenFingerPrint) {
            Intent in = new Intent(HimanshuProjectActivity.this, FingerPrintActHimanshu.class);
            startActivity(in);
        } else {
            Toast.makeText(this, "Your Device does not have a Fingerprint Sensor", Toast.LENGTH_SHORT).show();
            Intent in = new Intent(HimanshuProjectActivity.this, MainActivityHimanshu.class);
            startActivity(in);
        }
    }

    private void openHimanshuProject2() {
        Intent in=new Intent(HimanshuProjectActivity.this,OrderActivity.class);
        startActivity(in);
    }

    private void openHimanshuProject3() {
        Intent in=new Intent(HimanshuProjectActivity.this, LocationMainActivity.class);
        startActivity(in);
    }

    }