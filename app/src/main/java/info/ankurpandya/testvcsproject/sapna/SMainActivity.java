package info.ankurpandya.testvcsproject.sapna;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import info.ankurpandya.testvcsproject.R;

public class SMainActivity extends AppCompatActivity {
    EditText et_first,et_last;
    Button btn_Submit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);
        et_first=(EditText)findViewById(R.id.et_first);
        et_last=(EditText)findViewById(R.id.et_last);
        btn_Submit=(Button)findViewById(R.id.btn_Submit);
        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String info=("First Name : "+et_first.getText().toString()+"\nLast Name : "+et_last.getText().toString());


                Toast.makeText(SMainActivity.this, info, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
