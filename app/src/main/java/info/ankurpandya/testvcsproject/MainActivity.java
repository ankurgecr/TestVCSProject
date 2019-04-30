package info.ankurpandya.testvcsproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import info.ankurpandya.testvcsproject.ankur.TestBack4AppActivity;
import info.ankurpandya.testvcsproject.govind.LocationActivity;
import info.ankurpandya.testvcsproject.himanshu.HimanshuProjectActivity;
import info.ankurpandya.testvcsproject.sapna.SapnaProjectActivity;

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
        //Intent intent = new Intent(MainActivity.this, TestAnkurActivity.class);
        Intent intent = new Intent(MainActivity.this, TestBack4AppActivity.class);
        startActivity(intent);
    }

    private void openGovindTest() {
        Intent intent = new Intent(MainActivity.this, LocationActivity.class);
        startActivity(intent);
    }

    private void openSapnaTest() {
        Intent intent = new Intent(MainActivity.this, SapnaProjectActivity.class);
        startActivity(intent);
    }

    private void openHimanshuTest() {
        Intent in = new Intent(MainActivity.this, HimanshuProjectActivity.class);
        startActivity(in);

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
