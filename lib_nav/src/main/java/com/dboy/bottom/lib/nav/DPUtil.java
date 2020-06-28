package com.dboy.bottom.lib.nav;

import android.content.res.Resources;

/**
 * @author 夜斗
 * @date 2020/6/2
 * Class 描述 :
 */
class DPUtil {

    /**
     * Value of dp to value of px.
     *
     * @param dpValue The value of dp.
     * @return value of px
     */
    public static int dp2px(final float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
