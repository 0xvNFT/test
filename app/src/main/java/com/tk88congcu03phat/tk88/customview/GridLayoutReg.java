package com.tk88congcu03phat.tk88.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class GridLayoutReg extends ViewGroup {

    private int rows;
    private int columns;
    private Rect tempContainerRect = new Rect(); // used in onLayout, only to avoid object allocation there
    private Rect tempChildRect = new Rect(); // used in onLayout, only to avoid object allocation there

    public GridLayoutReg(Context context) {
        super(context);
    }

    public GridLayoutReg(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridLayoutReg(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("NewApi")
    public GridLayoutReg(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (getChildCount() != rows * columns) {
            throw new RuntimeException("Not enough children added!");
        }

        if (rows == 0 || columns == 0) {
            return;
        }

        int maxHeight = 0;
        int maxWidth = 0;

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {

                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
                maxWidth = Math.max(maxWidth, child.getMeasuredWidth());
                maxHeight = Math.max(maxHeight, child.getMeasuredHeight());
            }
        }

        int suggestedHeight = Math.max(maxHeight * rows, getSuggestedMinimumHeight());
        int suggestedWidth = Math.max(maxWidth * columns, getSuggestedMinimumWidth());

        setMeasuredDimension(resolveSize(suggestedWidth, widthMeasureSpec), resolveSize(suggestedHeight, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (getChildCount() != rows * columns) {
            throw new RuntimeException("Not enough children added!");
        }

        if (rows == 0 || columns == 0) {
            return;
        }

        final int inside_left = getPaddingLeft();
        final int inside_right = right - left - getPaddingRight();
        final int inside_top = getPaddingTop();
        final int inside_bottom = bottom - top - getPaddingBottom();

        float child_width = (inside_right - inside_left) / (float) columns;
        float child_height = (inside_bottom - inside_top) / (float) rows;

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                int index = row * columns + column;
                final View child = getChildAt(index);
                if (child.getVisibility() != GONE) {
                    final FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) child.getLayoutParams();

                    final int width = child.getMeasuredWidth();
                    final int height = child.getMeasuredHeight();

                    tempContainerRect.left = (int) (inside_left + column * child_width + lp.leftMargin);
                    tempContainerRect.right = (int) (inside_left + column * child_width + child_width - lp.rightMargin);
                    tempContainerRect.top = (int) (inside_top + row * child_height + lp.topMargin);
                    tempContainerRect.bottom = (int) (inside_top + row * child_height + child_height - lp.bottomMargin);

                    Gravity.apply(lp.gravity, width, height, tempContainerRect, tempChildRect);

                    child.layout(tempChildRect.left, tempChildRect.top, tempChildRect.right, tempChildRect.bottom);
                }
            }
        }
    }

    public int getRows(int rows) {
        return rows;
    }

    public void setRows(int rows) {
        if (rows < 0) {
            throw new RuntimeException("Negative number of rows not allowed.");
        }
        this.rows = rows;
    }

    public int getColumns(int columns) {
        return columns;
    }

    public void setColumns(int columns) {
        if (columns < 0) {
            throw new RuntimeException("Negative number of columns not allowed.");
        }
        this.columns = columns;
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new FrameLayout.LayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new FrameLayout.LayoutParams(p);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof FrameLayout.LayoutParams;
    }
}
