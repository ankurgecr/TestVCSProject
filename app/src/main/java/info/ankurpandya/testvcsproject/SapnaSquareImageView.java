package info.ankurpandya.testvcsproject;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by ParthSoni on 4/23/2019.
 */
public class SapnaSquareImageView extends AppCompatImageView {
    public SapnaSquareImageView(Context context) {
        super(context);
    }

    public SapnaSquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SapnaSquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    ImageView imageView;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        imageView=(ImageView)findViewById(R.id.siv);
        setMeasuredDimension(width, width);
    }
}
