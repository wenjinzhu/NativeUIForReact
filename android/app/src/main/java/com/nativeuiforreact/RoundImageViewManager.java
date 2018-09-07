package com.nativeuiforreact;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.nativeuiforreact.widget.RoundImageView;

/**
 * Created by king on 2018/9/6.
 */

public class RoundImageViewManager extends SimpleViewManager<RoundImageView> {

    public static final String COMPONENT_NAME = "RoundImageView";

    @Override
    public String getName() {
        return COMPONENT_NAME;
    }

    @Override
    protected RoundImageView createViewInstance(ThemedReactContext reactContext) {
        return new RoundImageView(reactContext);
    }

    @ReactProp(name = "name")
    public void setPath(RoundImageView view, String name) {
        view.setImageName(name);
    }

    /**
     * 设置图片类型：圆形、圆角矩形、椭圆形
     *
     * @param mType
     */
    @ReactProp(name = "type")
    public void setType(RoundImageView view, String mType) {
        view.setType(mType);
    }

    /**
     * 设置圆角大小
     *
     * @parammRoundRadius
     */
    @ReactProp(name = "roundradius")
    public void setRoundRadius(RoundImageView view, int mRoundRadius) {
        view.setRoundRadius(mRoundRadius);
    }
}
