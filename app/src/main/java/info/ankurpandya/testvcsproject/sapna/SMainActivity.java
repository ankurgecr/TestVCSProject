package info.ankurpandya.testvcsproject.sapna;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import info.ankurpandya.testvcsproject.R;

public class SMainActivity extends AppCompatActivity {

    KeyValueTextView txtKeyValue1, txtKeyValue2, txtKeyValue3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);

        txtKeyValue1 = findViewById(R.id.txt_keyvalue_1);
        txtKeyValue2 = findViewById(R.id.txt_keyvalue_2);
        txtKeyValue3 = findViewById(R.id.txt_keyvalue_3);

        txtKeyValue1.setText("My Name", "Sapna");
        txtKeyValue2.setText("Your Name", "Ankur");
        txtKeyValue3.setText("Company Name", "Twist Mobile");
    }
}
