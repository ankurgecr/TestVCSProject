package info.ankurpandya.testvcsproject;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SapnaMainActivity extends AppCompatActivity {
    RecyclerView rv;
    SapnaImageGridAdapter adapter;
    private static int RESULT_LOAD_IMAGE = 1;
    List<String> imagelist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sapna_activity_main);


        rv = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new SapnaImageGridAdapter(this, imagelist);
        rv.setLayoutManager(new GridLayoutManager(SapnaMainActivity.this, 2));
        rv.setAdapter(adapter);

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent, RESULT_LOAD_IMAGE);
        // startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_MULTIPLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            ClipData mClipData = data.getClipData();
            for (int i = 0; i < mClipData.getItemCount(); i++) {
                ClipData.Item item = mClipData.getItemAt(i);
                Uri selectedimage = item.getUri();
                String[] filePathColoum = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedimage, filePathColoum, null, null, null);
                while (cursor.moveToNext()) {
                    //cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColoum[0]);
                    String picturePath = cursor.getString(columnIndex);
                    System.out.println("picturepath:" + picturePath);
                    imagelist.add(picturePath);
                }
                cursor.close();
            }
            adapter.notifyDataSetChanged();
        }
    }

}
