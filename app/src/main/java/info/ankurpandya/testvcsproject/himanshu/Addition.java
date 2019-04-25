package info.ankurpandya.testvcsproject.himanshu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import info.ankurpandya.testvcsproject.R;

public class Addition extends AppCompatActivity {
    int minteger=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addition);
    }

    public void increaseInteger(View view){
        minteger = minteger+1;
        display(minteger);
    }
    public void decreaseInteger(View view){
        minteger= minteger-1;
        display(minteger);
    }

    private void display(int number) {
        TextView displayNumber=(TextView) findViewById(R.id.txt_number);
        displayNumber.setText("" +number);
    }

}
