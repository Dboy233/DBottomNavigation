package com.dboy.bottom.lib.nav;

import androidx.annotation.DrawableRes;

/**
 * @author 夜斗
 * @date 2020/6/2
 * Class 描述 :
 */
public class BackgroundConfig {
    /**
     * 背景
     */
    @DrawableRes
    private int backgroundRes;
    /**
     * 背景高度比
     */
    private float backgroundHeightPercent=-1;
    /**
     * 背景垂直位置比例
     */
    private float backgroundVerticalBias=-1;

    public BackgroundConfig( @DrawableRes int backgroundRes) {
        this.backgroundRes = backgroundRes;
    }

    public int getBackgroundRes() {
        return backgroundRes;
    }

    public BackgroundConfig setBackgroundRes(int backgroundRes) {
        this.backgroundRes = backgroundRes;
        return this;
    }

    public float getBackgroundHeightPercent() {
        return backgroundHeightPercent;
    }

    public BackgroundConfig setBackgroundHeightPercent( float backgroundHeightPercent) {
        this.backgroundHeightPercent = backgroundHeightPercent;
        return this;
    }

    public float getBackgroundVerticalBias() {
        return backgroundVerticalBias;
    }

    public BackgroundConfig setBackgroundVerticalBias(float backgroundVerticalBias) {
        this.backgroundVerticalBias = backgroundVerticalBias;
        return this;
    }
}
