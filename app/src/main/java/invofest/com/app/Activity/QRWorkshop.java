package invofest.com.app.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.zxing.Result;

import invofest.com.app.Converter.BaseResponse;
import invofest.com.app.Model.User;
import invofest.com.app.Network.ApiUtil;
import invofest.com.app.Network.PostEvent;
import invofest.com.app.R;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.CAMERA;

public class QRWorkshop extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;
    private static int camId = Camera.CameraInfo.CAMERA_FACING_BACK;
    private PostEvent postEvent;
    private User users = new User();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        int currentApiVersion = Build.VERSION.SDK_INT;

        if(currentApiVersion >=  Build.VERSION_CODES.KITKAT)
        {
            if(checkPermission())
            {
                Toast.makeText(getApplicationContext(), "Permission already granted!", Toast.LENGTH_LONG).show();
            }
            else
            {
                requestPermission();
            }
        }

        initView();


    }

    private void initView() {
        postEvent = ApiUtil.postEvent();
    }

    private boolean checkPermission()
    {
        return (ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    @Override
    public void onResume() {
        super.onResume();

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.KITKAT) {
            if (checkPermission()) {
                if(scannerView == null) {
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            } else {
                requestPermission();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        scannerView.stopCamera();
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0) {

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted){
                        Toast.makeText(getApplicationContext(), "Permission Granted, Now you can access camera", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access and camera", Toast.LENGTH_LONG).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CAMERA)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CAMERA},
                                                            REQUEST_CAMERA);
                                                }
                                            }
                                        });
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(QRWorkshop.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .create()
                .show();
    }

    @Override
    public void handleResult(final Result result) {
        final String myResult = result.getText();
        Log.d("QRCodeScanner", result.getText());
        Log.d("QRCodeScanner", result.getBarcodeFormat().toString());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ID Peserta Workshop");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Call<BaseResponse<User>> _Workshop = postEvent.absen_workshop(result.getText().toString());
                _Workshop.enqueue(new Callback<BaseResponse<User>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                        if (response.isSuccessful()){
                            BaseResponse<User> body = response.body();
                            if (body != null && body.getStatus()){
                                users = body.getData();
//                                Intent a = new Intent(QRWorkshop.this, WorkshopActivity.class);
//                                startActivity(a);
                                finish();
                                System.err.println("hasil "+response.body().getMessage());
                                System.err.println("hasile "+result.getText());

                            }else {
                                Toast.makeText(QRWorkshop.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }else {
                            System.err.println("seminaror "+ response.body().getMessage());
                            finish();
                            Toast.makeText(QRWorkshop.this, response.body().getMessage(), Toast.LENGTH_LONG).show();


                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                        Toast.makeText(QRWorkshop.this, "Gagal Scan Cuyy", Toast.LENGTH_SHORT).show();
                        System.err.println("seminar "+ t.getMessage());
                    }
                });
//                Toast.makeText(QRSeminar.this, "cek", Toast.LENGTH_SHORT).show();
            }
        });
//        builder.setNeutralButton("Visit", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(myResult));
//                startActivity(browserIntent);
//            }
//        });
        builder.setMessage(result.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();
    }
}

