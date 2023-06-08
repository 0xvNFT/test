package com.tk88congcu03phat.tk88.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.appcompat.app.AppCompatActivity;

import com.tk88congcu03phat.tk88.R;
import com.tk88congcu03phat.tk88.customview.GridLayoutReg;
import com.tk88congcu03phat.tk88.utils.ColoringUtilities;
import com.tk88congcu03phat.tk88.utils.Utils;

public class ColorHomePickActivity extends AppCompatActivity {

    public static final int PICK_COLOR_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_color);
        //back
        // back button action: set result as canceled and go back
//        ImageButton imageButton = findViewById(R.id.backButton);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setResult(RESULT_CANCELED);
//                finish();
//            }
//        });


        final GridLayoutReg layout = findViewById(R.id.colorSelectionGridLayout);
        layout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Utils.removeOnGlobalLayoutListener(layout, this);
                Context context = ColorHomePickActivity.this;
                float button_space_px = context.getResources().getDimension(R.dimen.color_selection_button_size) + 2 * context.getResources().getDimension(R.dimen.color_selection_button_margin);

                int width_px = layout.getWidth();
                int height_px = layout.getHeight();


                int columns = (int) Math.floor(width_px / button_space_px);
                int rows = (int) Math.floor(height_px / button_space_px);

                layout.setColumns(columns);
                layout.setRows(rows);


                LayoutInflater inflater = LayoutInflater.from(context);
                float[] hsv = new float[3];
                for (int row = 0; row < rows; row++) {
                    for (int column = 0; column < columns; column++) {

                        View view = inflater.inflate(R.layout.element_color_selection_grid, layout, false);
                        layout.addView(view);
                        if (column == 0) {

                            hsv[0] = 0;
                            hsv[1] = 0;
                            hsv[2] = (float) row / rows;
                        } else {

                            int index = (column - 1) * rows + row;
                            hsv[0] = (float) index / (rows * (columns - 1)) * 360;
                            if (index % 2 == 1) {
                                hsv[1] = 1;
                            } else {
                                hsv[1] = 0.6f;
                            }
                            hsv[2] = 1;
                        }

                        final int color = Color.HSVToColor(hsv);

                        int[] gradientColors = ColoringUtilities.colorSelectionButtonBackgroundGradient(color);

                        GradientDrawable drawable = (GradientDrawable) view.getBackground();
                        drawable.mutate();
                        drawable.setColors(gradientColors);

                        view.setOnClickListener(v -> ColorHomePickActivity.this.colorSelected(color));
                    }
                }

                layout.requestLayout();
            }
        });
    }

    private void colorSelected(int color) {
        Intent data = new Intent();
        data.putExtra("color", color);
        setResult(RESULT_OK, data);
        finish();
    }
}