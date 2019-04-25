package info.ankurpandya.testvcsproject;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;

import java.util.Calendar;

public class CustomDatePicker extends FrameLayout {
    EditText etDatePicker;
    DatePickerDialog pickerDialog;
    View v;

    public CustomDatePicker(Context context) {
        super(context);
        init();
    }

    public CustomDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomDatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomDatePicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    void init() {
        v = LayoutInflater.from(getContext()).inflate(R.layout.custom_date_picker, this, true);
        etDatePicker = v.findViewById(R.id.et_date_picker);
        etDatePicker.setInputType(InputType.TYPE_NULL);

        etDatePicker.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                pickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        etDatePicker.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                    }
                }, year, month, day);
                pickerDialog.show();


            }
        });
    }
}
