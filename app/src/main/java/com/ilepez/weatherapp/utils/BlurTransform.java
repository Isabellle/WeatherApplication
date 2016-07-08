package com.ilepez.weatherapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import com.squareup.picasso.Transformation;

import java.lang.ref.WeakReference;

/**
 * Created by Isabelle Lepez on 08/07/16.
 */
public final class BlurTransform implements Transformation {

    WeakReference<Context> context;

    public BlurTransform(Context context) {
        super();
        this.context = new WeakReference<>(context);
    }

    @Override
    public Bitmap transform(Bitmap bitmap) {
        RenderScript rs = RenderScript.create(context.get());
        Bitmap source = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        if (source == null) {
            return null;
        }

        Allocation input = Allocation.createFromBitmap(rs, source,
                Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT);
        Allocation output = Allocation.createTyped(rs, input.getType());
        ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setRadius(10);
        script.setInput(input);
        script.forEach(output);
        output.copyTo(source);

        // also darken the image
        Paint paint = new Paint();
        ColorFilter filter = new LightingColorFilter(Color.rgb(127, 127, 127),0);
        paint.setColorFilter(filter);
        Canvas canvas = new Canvas(source);
        canvas.drawBitmap(source, 0, 0, paint);

        bitmap.recycle();

        return source;
    }

    @Override
    public String key() {
        return "blur";
    }

}