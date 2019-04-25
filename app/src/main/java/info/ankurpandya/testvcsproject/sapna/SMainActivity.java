package info.ankurpandya.testvcsproject.sapna;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import info.ankurpandya.testvcsproject.R;

public class SMainActivity extends AppCompatActivity {
    TextView tv_first,tv_last;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);


    }
}
