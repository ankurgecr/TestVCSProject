package info.ankurpandya.testvcsproject.sapna;


import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import info.ankurpandya.testvcsproject.R;

/**
 * Created by ParthSoni on 4/25/2019.
 */
public class KeyValueTextView extends FrameLayout {

    View rootview;
    TextView txt_key, txt_Value;

    public KeyValueTextView(@NonNull Context context) {
        super(context);
        init();
    }

    public KeyValueTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public KeyValueTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public KeyValueTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        rootview = inflater.inflate(R.layout.key_value_textview, this, true);
        txt_key = (TextView) findViewById(R.id.txt_key);
        txt_Value = (TextView) findViewById(R.id.txt_value);
    }

    public void setText(String key, String value) {
        txt_key.setText(key);
        txt_Value.setText(value);
    }
}

