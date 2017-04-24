package com.jiduqingqian.recycleviewdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * Created by qianhao on 2017/4/21.
 * 滑动退出自定义页面
 */

public class SlidingFinishRelativeLayout extends RelativeLayout implements View.OnTouchListener {
    private OnSildingFinishListener onSildingFinishListener;
    private ViewGroup               parentView;
    private boolean                 isFinish;
    /**
     * 滑动的最小距离
     */
    private int                     mTouchSlop;
    /**
     * 按下点的X坐标
     */
    private int                     downX, downY;
    /**
     * 临时存储X坐标
     */
    private int tempX, tempY;
    /**
     * 滑动类
     */
    private Scroller mScroller;

    public SlidingFinishRelativeLayout(Context context) {
        super(context);
        init();
    }

    public SlidingFinishRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlidingFinishRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        parentView = (ViewGroup) getParent();
        mScroller = new Scroller(getContext());
    }

    public void setTouchView(View touchView) {
        touchView.setOnTouchListener(this);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        init();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                tempX = (int) motionEvent.getX();
                tempY = (int) motionEvent.getRawY();
                return true;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) motionEvent.getRawY();
                int deltaY = tempY - moveX;
                parentView.scrollTo(0, deltaY);
                //tempY = moveX;
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs(parentView.getScrollY()) > 150) {
                    scrollFinish();
                } else {
                    scrollOrigin();
                }
                break;
        }
        return true;
    }


    private void scrollOrigin() {
        isFinish = false;
        int scrollY = parentView.getScrollY();
        mScroller.startScroll(0, parentView.getScrollY(), 0, -scrollY, Math.abs(scrollY));
        postInvalidate();
    }

    private void scrollFinish() {
        isFinish = true;
        int scrollY;
        if (parentView.getScrollY() > 0) {//向上
            scrollY = getHeight() - parentView.getScrollY();
        } else {
            scrollY = -getHeight() - parentView.getScrollY();
        }
        mScroller.startScroll(0, parentView.getScrollY(), 0, scrollY + 1, Math.abs(scrollY));
        postInvalidate();
    }

    public void setOnSildingFinishListener(OnSildingFinishListener onSildingFinishListener) {
        this.onSildingFinishListener = onSildingFinishListener;
    }

    @Override
    public void computeScroll() {
        // 调用startScroll的时候scroller.computeScrollOffset()返回true，
        Log.d("qh", mScroller.isFinished() + "--" + isFinish);
        if (mScroller.computeScrollOffset()) {
            parentView.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
            if (mScroller.isFinished()) {
                if (onSildingFinishListener != null && isFinish) {
                    onSildingFinishListener.onSildingFinish();
                }
            }
        }
    }
}
