package info.ankurpandya.testvcsproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by ParthSoni on 4/25/2019.
 */
public class Edit_Sapna extends AppCompatActivity {
    TextView tv_first,tv_last;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_sapna);
        tv_first=(TextView)findViewById(R.id.txt_first);
        tv_last=(TextView)findViewById(R.id.txt_last);
    }
}
