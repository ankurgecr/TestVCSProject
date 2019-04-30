package info.ankurpandya.testvcsproject.ankur;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

import info.ankurpandya.testvcsproject.R;

/**
 * Created by ParthSoni on 4/30/2019.
 */
public class TestBack4AppActivity extends AppCompatActivity {

    private static final String STUDENT_TABLE = "Student";
    private static final String STUDENT_ROW_NAME = "name";
    private static final String STUDENT_ROW_ROLLNO = "rollNumber";
    private static final String STUDENT_ROW_EMAIL = "email";
    private static final String STUDENT_ROW_DP = "dp";
    private static final String STUDENT_ROW_MARKS = "marks";

    private static final String TAG = "Back4App";

    private EditText edtRollNo;
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtMarks;
    private Button btnSave;
    private ProgressBar progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_back4app_test);

        edtRollNo = findViewById(R.id.edt_roll_no);
        edtName = findViewById(R.id.edt_name);
        edtEmail = findViewById(R.id.edt_email);
        edtMarks = findViewById(R.id.edt_marks);
        btnSave = findViewById(R.id.btn_save);
        progress = findViewById(R.id.progress);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rollNo = Integer.parseInt(edtRollNo.getText().toString());
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                int marks = Integer.parseInt(edtMarks.getText().toString());
                addNewStudent(rollNo, name, email, marks);
            }
        });
    }

    private void updateAbc() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(STUDENT_TABLE);
        query.whereEqualTo(STUDENT_ROW_NAME, "Abc");
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    object.put(STUDENT_ROW_MARKS, 100);
                    object.saveInBackground();
                } else {
                    Log.e(TAG, "Update Error: [" + e.getMessage() + "]");
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //getStudentData();
        updateAbc();
    }

    private void addNewStudent(int rollNumber, String name, String email, int marks) {
        ParseObject studentObject = new ParseObject(STUDENT_TABLE);
        studentObject.put(STUDENT_ROW_ROLLNO, rollNumber);
        studentObject.put(STUDENT_ROW_NAME, name);
        studentObject.put(STUDENT_ROW_EMAIL, email);
        studentObject.put(STUDENT_ROW_MARKS, marks);

        showProgress();

        studentObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                hideProgress();
                if (e == null) {
                    //Save Success
                    Toast.makeText(TestBack4AppActivity.this, "Student saved successfully", Toast.LENGTH_SHORT).show();
                } else {
                    //Save error
                    Log.e(TAG, "Save Error: [" + e.getMessage() + "]");
                    Toast.makeText(TestBack4AppActivity.this, "Error while saving Student", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getStudentData() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(STUDENT_TABLE);
        query.whereGreaterThan(STUDENT_ROW_MARKS, 50);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    if (objects != null && objects.size() > 0) {
                        for (ParseObject object : objects) {
                            String name = object.getString(STUDENT_ROW_NAME);
                            int rollNo = object.getInt(STUDENT_ROW_ROLLNO);
                            int marks = object.getInt(STUDENT_ROW_MARKS);
                            String email = object.getString(STUDENT_ROW_EMAIL);
                            ParseFile dp = object.getParseFile(STUDENT_ROW_DP);
                            if (dp != null) {
                                String dpUrl = dp.getUrl();
                                //ToDo - Picasso
                            }
                            Log.d(TAG, "Student [" + rollNo + "][" + name + "][" + email + "][" + marks + "]");
                        }
                    } else {
                        Log.d(TAG, "No matching data found");
                    }
                } else {
                    Log.e(TAG, "Query Error: [" + e.getMessage() + "]");
                }
            }
        });
    }

    private void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        progress.setVisibility(View.GONE);
    }

}
