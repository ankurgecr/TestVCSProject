package info.ankurpandya.testvcsproject.sapna;

import android.Manifest;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import info.ankurpandya.testvcsproject.R;
import info.ankurpandya.testvcsproject.SapnaImageGridAdapter;

/**
 * Created by ParthSoni on 4/26/2019.
 */
public class ImagePickerActivity extends AppCompatActivity {

    private static final String[] PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };
    private static final int REQUEST_LOAD_IMAGE_GALLERY = 100;
    private static final int REQUEST_LOAD_IMAGE_CAMERA = 101;
    private static final int REQUEST_IMAGE_PERMISSION = 300;

    RecyclerView recyclerView;
    TextView txtDeny;
    Button btnAllow;

    SapnaImageGridAdapter adapter;

    List<Bitmap> selectedImages = new ArrayList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        startMainTask();
    }

    void startMainTask() {
        if (hasEnoughPermissions()) {
            showCameraOrGalleryDialog();
        } else {
            requestRequiredPermissions();
        }
    }

    private void initViews() {
        setContentView(R.layout.sapna_activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        txtDeny = findViewById(R.id.txt_deny);
        btnAllow = findViewById(R.id.btn_allow);
        hidePermissionDeniedError();

        adapter = new SapnaImageGridAdapter(selectedImages);
        recyclerView.setAdapter(adapter);

        btnAllow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestRequiredPermissions();
            }
        });
    }

    private boolean hasEnoughPermissions() {
        for (String permission : PERMISSIONS) {
            int permissionResult = ContextCompat.checkSelfPermission(
                    this,
                    permission
            );
            if (permissionResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private void showCameraOrGalleryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick Image from");
        builder.setMessage("From where you want to pick images?");
        builder.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handleCameraSelected();
            }
        });
        builder.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handleGallerySelected();
            }
        });
        builder.show();
    }

    private void requestRequiredPermissions() {
        ActivityCompat.requestPermissions(this,
                PERMISSIONS,
                REQUEST_IMAGE_PERMISSION
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == REQUEST_IMAGE_PERMISSION) {
            boolean anyOnePermissionDenied = false;
            for (int i = 0; i < grantResults.length; i++) {
                int grantResult = grantResults[i];
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    anyOnePermissionDenied = true;
                    break;
                }
            }
            if (anyOnePermissionDenied) {
                handlePermissionDenied();
            } else {
                handlePermissionAccepted();
            }
        }
    }

    private void handlePermissionAccepted() {
        showCameraOrGalleryDialog();
    }

    private void handlePermissionDenied() {
        showPermissionDeniedError();
    }

    private void handleCameraSelected() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_LOAD_IMAGE_CAMERA);
    }

    private void handleGallerySelected() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent, REQUEST_LOAD_IMAGE_GALLERY);
    }

    private void showPermissionDeniedError() {
        txtDeny.setVisibility(View.VISIBLE);
        btnAllow.setVisibility(View.VISIBLE);
    }

    private void hidePermissionDeniedError() {
        txtDeny.setVisibility(View.GONE);
        btnAllow.setVisibility(View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOAD_IMAGE_GALLERY) {
            if (resultCode == RESULT_OK) {
                if (null != data) {
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
                            Bitmap bitmap = convertPathToBitmap(picturePath);
                            if (bitmap != null) {
                                selectedImages.add(bitmap);
                            }
                        }
                        cursor.close();
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    showErrorMessage("No image is selected.");
                }
            } else {
                showErrorMessage("Error while selecting image from gallery");
            }
        } else if (requestCode == REQUEST_LOAD_IMAGE_CAMERA) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            selectedImages.add(photo);
            adapter.notifyDataSetChanged();
        }
    }

    private void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private Bitmap convertPathToBitmap(String imagePath) {
        File imgFile = new File(imagePath);
        if (imgFile.exists()) {
            return BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        }
        return null;
    }
}
