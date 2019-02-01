package com.nestnfly.event.custom;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import com.nestnfly.event.helper.Functions;


public class TfEditText extends AppCompatEditText {

    private Context _ctx;

    public TfEditText(Context context) {
        super(context);
        if (!isInEditMode()) {
            this._ctx = context;
            init();
        }
    }

    public TfEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            this._ctx = context;
            init();
        }
    }

    private void init() {
        try {
            setTypeface(Functions.getRegularFont(_ctx));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
