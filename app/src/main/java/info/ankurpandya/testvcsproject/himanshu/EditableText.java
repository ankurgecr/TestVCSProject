package info.ankurpandya.testvcsproject.himanshu;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import info.ankurpandya.testvcsproject.R;

public class EditableText extends FrameLayout {

    View rootView;
    EditText etNum;
    Button btnIncre, btnDecre;

    public EditableText(@NonNull Context context) {
        super(context);
        init();
    }

    public EditableText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditableText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EditableText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        rootView = inflater.inflate(R.layout.addition, this, true);
        etNum = findViewById(R.id.et_number);
        btnIncre = findViewById(R.id.btn_increase);
        btnDecre = findViewById(R.id.btn_decrease);
        etNum.setText("0");

        btnIncre.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseInteger();
            }
        });

        btnDecre.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseInteger();
            }
        });
    }

    public void increaseInteger() {
        display(getCurrentValue() + 1);
    }

    public void decreaseInteger() {
        display(getCurrentValue() - 1);
    }

    public int getCurrentValue() {
        return Integer.parseInt(etNum.getText().toString());
    }

    private void display(int number) {
        etNum.setText("" + number);
    }

}

