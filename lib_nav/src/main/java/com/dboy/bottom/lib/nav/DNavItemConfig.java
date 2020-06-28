package com.dboy.bottom.lib.nav;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;

/**
 * @author 夜斗
 * @date 2020/6/2
 * Class 描述 :
 */
public class DNavItemConfig {

    @DrawableRes
    private int iconDrawable = -1;

    private CharSequence itemName;

    @ColorInt
    private int nameColor;

    private int nameSizeSp;

    private float zoomSize=1;

    public DNavItemConfig(@DrawableRes int iconDrawable, CharSequence itemName) {
        this.iconDrawable = iconDrawable;
        this.itemName = itemName;
    }

    public float getZoomSize() {
        return zoomSize;
    }

    public DNavItemConfig setZoomSize(float zoomSize) {
        this.zoomSize = zoomSize;
        return this;
    }

    public int getNameColor() {
        return nameColor;
    }

    public DNavItemConfig setNameColor(@ColorInt int nameColor) {
        this.nameColor = nameColor;
        return this;
    }

    public int getNameSizeSp() {
        return nameSizeSp;
    }

    public DNavItemConfig setNameSizeSp(int nameSizeSp) {
        this.nameSizeSp = nameSizeSp;
        return this;
    }

    public int getIconDrawable() {
        return iconDrawable;
    }

    public DNavItemConfig setIconDrawable(int iconDrawable) {
        this.iconDrawable = iconDrawable;
        return this;
    }

    public CharSequence getItemName() {
        return itemName;
    }

    public DNavItemConfig setItemName(CharSequence itemName) {
        this.itemName = itemName;
        return this;
    }


}
