package info.ankurpandya.testvcsproject.sapna;


import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import info.ankurpandya.testvcsproject.R;

/**
 * Created by ParthSoni on 4/25/2019.
 */
public class EditableText extends FrameLayout {
    View rootview;
    EditText et_first,et_last;

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

    private void init() {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        rootview=inflater.inflate(R.layout.activity_main_two,this,true);
        et_first=(EditText) findViewById(R.id.et_first);
        et_last=(EditText) findViewById(R.id.et_last);
        et_first.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et_first.setText("");
                et_last.setText("");

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}

