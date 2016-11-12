package com.example.hhj.fiction_reader.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hhj.fiction_reader.R;

/**
 * Created by XX on 2016/10/10.
 */
public class MyToolBar extends Toolbar {
    private LayoutInflater mInflater;
    private View mView;
    private TextView mTextView;
    private Button mRightButton;

    public MyToolBar(Context context) {
        this(context,null);
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs) {
        this(context, null,0);
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        if(attrs != null){
            final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(),attrs,
                    R.styleable.MyToolBar,defStyleAttr,0);
            final Drawable rightIcon = a.getDrawable(R.styleable.MyToolBar_rightButtonIcon);

            CharSequence rightButtonText = a.getText(R.styleable.MyToolBar_rightButtonText);
        }
    }

    private void initView() {

        if(mView == null){
            mInflater = LayoutInflater.from(getContext());
            mView = mInflater.inflate(R.layout.mytoolbar,null);
            mTextView = (TextView) mView.findViewById(R.id.toolbar_title);
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
            addView(mView,lp);

        }

    }

    @Override
    public void setTitle(@StringRes int resId) {
        super.setTitle(resId);
    }

    @Override
    public void setTitle(CharSequence title) {
        initView();
        if(mTextView != null){
            mTextView.setText(title);

        }
        super.setTitle(title);
    }
}
