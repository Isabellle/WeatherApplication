package com.ilepez.weatherapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by Isabelle Lepez on 08/07/16.
 */
public final class GlideBlurTransform extends BitmapTransformation {

    private RenderScript mRenderScript;

    public GlideBlurTransform(Context context) {
        super(context);
        mRenderScript = RenderScript.create(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Bitmap blurredBitmap = toTransform.copy( Bitmap.Config.ARGB_8888, true );

        // Allocate memory for Renderscript to work with
        Allocation input = Allocation.createFromBitmap(
                mRenderScript,
                blurredBitmap,
                Allocation.MipmapControl.MIPMAP_FULL,
                Allocation.USAGE_SHARED
        );
        Allocation output = Allocation.createTyped(mRenderScript, input.getType());

        // Load up an instance of the specific script that we want to use.
        ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(mRenderScript, Element.U8_4(mRenderScript));
        script.setInput(input);

        // Set the blur radius
        script.setRadius(10);

        // Start the ScriptIntrinisicBlur
        script.forEach(output);

        // Copy the output to the blurred bitmap
        output.copyTo(blurredBitmap);

        toTransform.recycle();

        return blurredBitmap;
    }

//    @Override
//    public Bitmap transform(Bitmap bitmap) {
//        RenderScript rs = RenderScript.create(context.get());
//        Bitmap source = bitmap.copy(Bitmap.Config.ARGB_8888, true);
//
//        if (source == null) {
//            return null;
//        }
//
//        Allocation input = Allocation.createFromBitmap(rs, source,
//                Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT);
//        Allocation output = Allocation.createTyped(rs, input.getType());
//        ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
//        script.setRadius(10);
//        script.setInput(input);
//        script.forEach(output);
//        output.copyTo(source);
//
//        // also darken the image
//        Paint paint = new Paint();
//        ColorFilter filter = new LightingColorFilter(Color.rgb(127, 127, 127),0);
//        paint.setColorFilter(filter);
//        Canvas canvas = new Canvas(source);
//        canvas.drawBitmap(source, 0, 0, paint);
//
//        bitmap.recycle();
//
//        return source;
//    }

    @Override
    public String getId() {
        return "blur";
    }
}