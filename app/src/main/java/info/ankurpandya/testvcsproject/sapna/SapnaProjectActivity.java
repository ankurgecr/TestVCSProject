package info.ankurpandya.testvcsproject.sapna;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import info.ankurpandya.testvcsproject.R;
import info.ankurpandya.testvcsproject.SapnaMainActivity;

public class SapnaProjectActivity extends AppCompatActivity {

    Button btnPro1,btnPro2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sapna_project_list);
        btnPro1=(Button)findViewById(R.id.btn_pro_1);
        btnPro2=(Button)findViewById(R.id.btn_pro_2);
        btnPro1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SapnaProjectActivity.this, SapnaMainActivity.class);
                startActivity(intent);

            }
        });
        btnPro2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SapnaProjectActivity.this,SMainActivity.class);
                startActivity(intent);
            }
        });
    }
}
