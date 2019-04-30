package info.ankurpandya.testvcsproject.sapna;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import info.ankurpandya.testvcsproject.R;
import info.ankurpandya.testvcsproject.sapna.SapnaGame.GameActivity;

public class SapnaProjectActivity extends AppCompatActivity {

    Button btnPro1,btnPro2,btnPro3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sapna_project_list);
        btnPro1=(Button)findViewById(R.id.btn_pro_1);
        btnPro2=(Button)findViewById(R.id.btn_pro_2);
        btnPro3=(Button)findViewById(R.id.btn_pro_3);
        btnPro1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SapnaProjectActivity.this, ImagePickerActivity.class);
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
        btnPro3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SapnaProjectActivity.this, GameActivity.class);
                startActivity(intent);

            }
        });
    }
}
