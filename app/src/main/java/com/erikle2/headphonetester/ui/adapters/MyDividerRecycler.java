package com.erikle2.headphonetester.ui.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.erikle2.headphonetester.R;

/**
 * Created by Erik on 15/04/2016.
 */
public class MyDividerRecycler extends  RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.divider, R.drawable.divider};

    private Drawable mDivider;

    /**
     * Default divider will be used
     */
    public MyDividerRecycler(Context context) {
//        final TypedArray styledAttributes = context.obtainStyledAttributes(ATTRS);
//        mDivider = styledAttributes.getDrawable(1);
//        styledAttributes.recycle();
        mDivider = ContextCompat.getDrawable(context, ATTRS[1]);
    }

    /**
     * Custom divider will be used
     */
    public MyDividerRecycler(Context context, int resId) {
        mDivider = ContextCompat.getDrawable(context, resId);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = 10;
        outRect.left = 40;
        outRect.right = 40;
        outRect.bottom = 40;
    }
//
//    @Override
//    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        int left = parent.getPaddingLeft();
//        int right = parent.getWidth() - parent.getPaddingRight();
//
//        int childCount = parent.getChildCount();
//        for (int i = 0; i < childCount-1; i++) {
//            View child = parent.getChildAt(i);
//
//            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
//
//            int top = child.getBottom() + params.bottomMargin;
//            int bottom = top + mDivider.getIntrinsicHeight();
//
//            mDivider.setBounds(left, top, right, bottom);
//            mDivider.draw(c);
//        }
//    }
}
