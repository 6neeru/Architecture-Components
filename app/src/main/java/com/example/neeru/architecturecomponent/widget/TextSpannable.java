package com.example.neeru.architecturecomponent.widget;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

public class TextSpannable extends ClickableSpan {
    private boolean isUnderline = true;

    /**
     * Constructor
     */
    public TextSpannable(boolean isUnderline) {
        this.isUnderline = isUnderline;
    }

    @Override
    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(isUnderline);
        textPaint.setColor(Color.parseColor("#0283BA"));
    }

    @Override
    public void onClick(View widget) {


    }
}
