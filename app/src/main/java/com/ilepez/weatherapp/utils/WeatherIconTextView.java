package com.ilepez.weatherapp.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Isabelle Lepez on 07/07/16.
 */
public class WeatherIconTextView extends TextView {

    public WeatherIconTextView(Context context) {
        super(context);
    }

    public WeatherIconTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WeatherIconTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        if (!this.isInEditMode()) {
            if (style == Typeface.NORMAL) {
                super.setTypeface(FontCache.getFont(getContext(), "fonts/weathericons-regular-webfont.ttf"));
            } else if (style == Typeface.ITALIC) {
                super.setTypeface(FontCache.getFont(getContext(), "fonts/weathericons-regular-webfont.ttf"));
            } else if (style == Typeface.BOLD) {
                super.setTypeface(FontCache.getFont(getContext(), "fonts/weathericons-regular-webfont.ttf"));
            }
        }
    }
}
