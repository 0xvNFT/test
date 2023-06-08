package com.tk88congcu03phat.tk88.ui.home;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tk88congcu03phat.tk88.R;
import com.tk88congcu03phat.tk88.customview.ViewerCustomization;
import com.tk88congcu03phat.tk88.data.model.MainEventsMod;
import com.tk88congcu03phat.tk88.databinding.ActivityImageBinding;
import com.tk88congcu03phat.tk88.utils.AppUtilities;
import com.tk88congcu03phat.tk88.utils.ColoringUtilities;
import com.tk88congcu03phat.tk88.utils.Library;
import com.tk88congcu03phat.tk88.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;



public class ImageHomeActivity extends AppCompatActivity {

    public static int colorCurrent = 0;

    private ActivityImageBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.layoutHeader.imageBack.setOnClickListener(view -> finish());
        ViewerCustomization coloringView = binding.imageMainR;
        ViewTreeObserver vto = coloringView.getViewTreeObserver();
        updateColorOfColorPickerButton(R.color.color_fb);
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Utils.removeOnGlobalLayoutListener(coloringView, this);
                Bitmap myLogo = BitmapFactory.decodeResource(getResources(), HomeFrags.imageMainSelect.paths);

                coloringView.setBitmap(myLogo);
                coloringView.invalidate();
            }
        });

        binding.colorPickerButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, ColorHomePickActivity.class);
            startActivityForResult(intent, ColorHomePickActivity.PICK_COLOR_REQUEST);

        });

        binding.textLike.setOnClickListener(view -> {
            AppUtilities.saveImageLike(HomeFrags.imageMainSelect);
            new Handler().postDelayed(() -> EventBus.getDefault().post(new MainEventsMod()), 2000);
        });

        binding.textSave.setOnClickListener(view -> {
            if (!coloringView.isModified()) {

                finish();
            } else {
                final Context context = this;

                boolean externalStorageAvailable = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
                if (!externalStorageAvailable) {
                    Toast toast = Toast.makeText(context, getString(R.string.toast_external_storage_unavailable), Toast.LENGTH_SHORT);
                    toast.show();
                }

                DialogInterface.OnClickListener finishListener = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                };

                DialogInterface.OnClickListener saveImageListener = (dialog, id) -> {


                    File externalStorageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    File base_directory = new File(externalStorageDir, "book");
//                        File base_directory = new File(externalStorageDir, Library.getInstance().getStringFromCurrentBook("name"));


                    base_directory.mkdirs();


                    String name = Library.getInstance().getStringFromCurrentPage("file");

                    name = name.substring(0, name.length()-4);
                    SimpleDateFormat sdf = new SimpleDateFormat("_yyyy-MM-dd_HH-mm"); // or should we use getDateInstance(), getDateTimeInstance() for locale formatting
                    name += sdf.format(new Date());
                    name += ".png";
                    Log.v("COL", name);
                    File file = new File(base_directory, name);


                    if (file.exists()) {
                        file.delete();
                    }

                    OutputStream out;
                    try {
                        out = new FileOutputStream(file);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                    Bitmap bitmap = coloringView.getBitmap();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);


                    MediaScannerConnection.scanFile(context, new String[]{file.toString()}, null, null);


                    finish();
                };


                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.coloring_end_dialog_title);


                builder.setNegativeButton(R.string.dialog_cancel, null);

                if (externalStorageAvailable) {

                    builder.setNeutralButton(R.string.coloring_end_dialog_neutral, finishListener);

                    builder.setPositiveButton(R.string.coloring_end_dialog_positive, saveImageListener);
                } else {

                    builder.setPositiveButton(R.string.coloring_end_dialog_neutral, finishListener);
                }

                AlertDialog dialog = builder.create();
                dialog.show();
        }});
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ColorHomePickActivity.PICK_COLOR_REQUEST && resultCode == RESULT_OK) {
            int color = data.getIntExtra("color", 0);

            updateColorOfColorPickerButton(color);
        }
    }

    @SuppressLint("ResourceType")
    private void updateColorOfColorPickerButton(int color) {
        colorCurrent = color;
        View view = findViewById(R.id.colorPickerButton);


        int[] gradientColors = ColoringUtilities.colorSelectionButtonBackgroundGradient(color);

        GradientDrawable drawable = (GradientDrawable) view.getBackground();
        drawable.mutate();
        drawable.setColors(gradientColors);
    }

}
