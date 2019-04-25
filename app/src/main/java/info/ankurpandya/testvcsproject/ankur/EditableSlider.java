package info.ankurpandya.testvcsproject.ankur;

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
import android.widget.SeekBar;

import info.ankurpandya.testvcsproject.R;

public class EditableSlider extends FrameLayout {

    View rootView;
    SeekBar seekProgress;
    EditText edtProgress;

    public EditableSlider(@NonNull Context context) {
        super(context);
        init();
    }

    public EditableSlider(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditableSlider(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EditableSlider(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        rootView = inflater.inflate(R.layout.editable_slider, this, true);
        seekProgress = rootView.findViewById(R.id.seek_progress);
        edtProgress = rootView.findViewById(R.id.etd_progress);

        seekProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                edtProgress.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        edtProgress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    int progressInt = Integer.parseInt(charSequence.toString());
                    if (progressInt <= seekProgress.getMax() && progressInt >= 0) {
                        seekProgress.setProgress(progressInt);
                    } else {
                        edtProgress.setText("");
                        edtProgress.setError("Invalid value");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
