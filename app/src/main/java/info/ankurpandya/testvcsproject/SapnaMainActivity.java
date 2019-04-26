package info.ankurpandya.testvcsproject;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SapnaMainActivity extends AppCompatActivity {
    RecyclerView rv;
    SapnaImageGridAdapter adapter;
    private static int RESULT_LOAD_IMAGE = 1;
    List<String> imagelist = new ArrayList<>();

    private static String TAG = "PermissionDemo";
    private static final int WRITE_REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sapna_activity_main);

        rv = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new SapnaImageGridAdapter(this, imagelist);
        rv.setLayoutManager(new GridLayoutManager(SapnaMainActivity.this, 2));
        rv.setAdapter(adapter);


    }

    @Override
    protected void onStart() {
        super.onStart();

        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission to record denied");
            makeRequest();
        }
        else {

            openImagePicker();
        }
        // startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_MULTIPLE);
    }

    private  void openImagePicker(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent, RESULT_LOAD_IMAGE);
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

    protected void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                WRITE_REQUEST_CODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case WRITE_REQUEST_CODE: {

                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED) {

                    Log.i(TAG, "Permission has been denied by user");
                } else {
                    Log.i(TAG, "Permission has been granted by user");
                    openImagePicker();
                }
                return;
            }
        }
    }
}
