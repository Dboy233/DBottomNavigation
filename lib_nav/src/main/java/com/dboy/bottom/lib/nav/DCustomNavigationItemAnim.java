package com.dboy.bottom.lib.nav;

import android.animation.AnimatorSet;
import android.view.View;

/**
 * @author 夜斗
 * @date 2020/6/2
 * Class 描述 :
 */
public interface DCustomNavigationItemAnim {
    /**
     * 设置自定义动画
     * @param icon 图标视图
     * @param name 名字视图
     * @param isSelected
     * @return 动画set
     */
    AnimatorSet setAnim(View icon, View name, boolean isSelected);
}
