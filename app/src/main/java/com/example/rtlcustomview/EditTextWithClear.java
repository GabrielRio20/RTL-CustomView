package com.example.rtlcustomview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;

public class EditTextWithClear extends AppCompatEditText {
    Drawable clearButtonImage;

    private void init(){
        clearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.close50, null);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                showClearButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(getCompoundDrawablesRelative()[0] != null){
                    float clearButtonStartPosition = (getWidth()-getPaddingEnd()
                            -clearButtonImage.getIntrinsicWidth());
                    boolean isButtonClicked = false;

                    if(event.getX() < clearButtonStartPosition){
                        isButtonClicked = true;
                    }

                    if(isButtonClicked){
                        if(event.getAction() == MotionEvent.ACTION_DOWN){
                            clearButtonImage = ResourcesCompat.getDrawable
                                    (getResources(), R.drawable.close100, null);
                            showClearButton();
                        }
                        if(event.getAction() == MotionEvent.ACTION_UP){
                            clearButtonImage = ResourcesCompat.getDrawable
                                    (getResources(), R.drawable.close50, null);
                            getText().clear();
                            hideClearButton();
                            return true;
                        }
                    }
                    else{
                        return false;
                    }
                }


                return false;
            }
        });
    }
    public EditTextWithClear(@NonNull Context context) {
        super(context);
        init();
    }

    public EditTextWithClear(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextWithClear(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void showClearButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(clearButtonImage, null, null, null);
    }

    private void hideClearButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
    }
}