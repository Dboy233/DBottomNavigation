package com.dboy.bottom.lib.nav;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;


/**
 * @author 夜斗
 * @date 2020/6/2
 * Class 描述 :
 */
public class DNavigationItem extends FrameLayout {
    /**
     * 默认边距
     */
    private final int DEF_MARGIN = 16;
    /**
     * 是否默认选中
     */
    public boolean isDefSelected = false;
    /**
     * 显示数据
     */
    private DNavItemConfig mData;
    /**
     * Icon
     */
    private ImageView mItemIcon;
    /**
     * name
     */
    private TextView mItemName;
    /**
     * 控件占比
     */
    private float weight = 1;
    /**
     * 是否已经显示在用户眼睛下
     */
    private boolean isShow = false;
    /**
     * 点击选中动画效果
     */
    private AnimatorSet mDefAnim;
    /**
     * 自定义点击选中动画效果
     */
    private AnimatorSet mCustomizeAnim;
    /**
     * 自定义动画接口
     */
    private DCustomNavigationItemAnim mDCustomNavigationItemAnim;
    /**
     * 顶部边距
     */
    private int mIconTopMargin = DEF_MARGIN;
    /**
     * icon和name之间的边距
     */
    private int mIconNameMargin = 0;


    public DNavigationItem(@NonNull Context context, DNavItemConfig data) {
        this(context, data, false);
    }

    public DNavigationItem(@NonNull Context context, DNavItemConfig data, boolean defSelected) {
        this(context, data, defSelected, 1);
    }

    public DNavigationItem(@NonNull Context context, DNavItemConfig data, float weight) {
        this(context, data, false, weight);
    }

    public DNavigationItem(@NonNull Context context, DNavItemConfig data, boolean defSelected, float weight) {
        super(context);
        mData = data;
        this.weight = weight;
        isDefSelected = defSelected;
        setClipChildren(false);
        init(context);
    }

    private void init(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_bottom_nav_item, this);
        mItemIcon = inflate.findViewById(R.id.nav_item_icon);
        mItemName = inflate.findViewById(R.id.nav_item_name);
        mItemIcon.post(new Runnable() {
            @Override
            public void run() {
                int height = mItemIcon.getHeight();
                mItemIcon.setPivotY(height);
            }
        });

    }

    public DNavigationItem setNameTextSize(int sp) {
        mItemName.setTextSize(sp);
        return this;
    }

    private void setRes() {
        if (mData.getIconDrawable() != -1) {
            mItemIcon.setImageResource(mData.getIconDrawable());
        }
        if (mData.getItemName() != null) {
            mItemName.setText(mData.getItemName());
        }
        if (mData.getNameColor() != 0) {
            mItemName.setTextColor(mData.getNameColor());
        }
        if (mData.getNameSizeSp() != 0) {
            mItemName.setTextSize(mData.getNameSizeSp());
        }
        if (mData.getZoomSize() != 1) {
            mItemIcon.post(new Runnable() {
                @Override
                public void run() {
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) mItemIcon.getLayoutParams();
                    layoutParams.height *= mData.getZoomSize();
                    layoutParams.width *= mData.getZoomSize();
                    mItemIcon.setLayoutParams(layoutParams);
                    mItemIcon.setPivotY(layoutParams.height);
                    mItemIcon.setPivotX(layoutParams.width / 2.0f);
                }
            });
        }

        if (isDefSelected) {
            post(new Runnable() {
                @Override
                public void run() {
                    setSelected(isDefSelected);
                }
            });
        }
    }

    public DNavItemConfig getData() {
        return mData;
    }

    public DNavigationItem setData(DNavItemConfig data) {
        mData = data;
        setRes();
        return this;
    }

    /**
     * 设置顶部边距
     *
     * @param topMargin 边距
     */
    public DNavigationItem setTopMargin(final int topMargin) {
        mIconTopMargin = topMargin;
        mItemIcon.post(new Runnable() {
            @Override
            public void run() {
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) mItemIcon.getLayoutParams();
                layoutParams.setMargins(layoutParams.leftMargin, DPUtil.dp2px(mIconTopMargin), layoutParams.rightMargin, layoutParams.bottomMargin);
                mItemIcon.setLayoutParams(layoutParams);
            }
        });
        return this;
    }

    @Override
    public void setSelected(boolean selected) {
        if (isSelected() == selected) {
            return;
        }
        super.setSelected(selected);
        mItemIcon.setSelected(selected);
        startSelectedAnim(selected);
    }

    /**
     * 改变选中状态
     */
    public void onSelected() {
        setSelected(!isSelected());
    }

    /**
     * 开启动画
     *
     * @param selected
     */
    private void startSelectedAnim(boolean selected) {
        if (mDCustomNavigationItemAnim != null) {
            mCustomizeAnim = mDCustomNavigationItemAnim.setAnim(mItemIcon, mItemName, selected);
            if (mCustomizeAnim != null) {
                mCustomizeAnim.cancel();
                mCustomizeAnim.start();
            }
            return;
        }
        //默认动画
        if (mDefAnim != null) {
            mDefAnim.cancel();
            mDefAnim = null;
        }
        mDefAnim = new AnimatorSet();
        if (selected) {
            ObjectAnimator scaleXIcon = ObjectAnimator.ofFloat(mItemIcon, View.SCALE_X, mItemIcon.getScaleX(), 1.2f);
            ObjectAnimator scaleYIcon = ObjectAnimator.ofFloat(mItemIcon, View.SCALE_Y, mItemIcon.getScaleY(), 1.2f);
            ObjectAnimator scaleXName = ObjectAnimator.ofFloat(mItemName, View.SCALE_X, mItemName.getScaleX(), 1.1f);
            ObjectAnimator scaleYName = ObjectAnimator.ofFloat(mItemName, View.SCALE_Y, mItemName.getScaleY(), 1.1f);
            mDefAnim.playTogether(scaleXIcon, scaleYIcon, scaleXName, scaleYName);
        } else {
            ObjectAnimator scaleXIcon = ObjectAnimator.ofFloat(mItemIcon, View.SCALE_X, mItemIcon.getScaleX(), 1);
            ObjectAnimator scaleYIcon = ObjectAnimator.ofFloat(mItemIcon, View.SCALE_Y, mItemIcon.getScaleY(), 1);
            ObjectAnimator scaleXName = ObjectAnimator.ofFloat(mItemName, View.SCALE_X, mItemName.getScaleX(), 1);
            ObjectAnimator scaleYName = ObjectAnimator.ofFloat(mItemName, View.SCALE_Y, mItemName.getScaleY(), 1);
            mDefAnim.playTogether(scaleXIcon, scaleYIcon, scaleXName, scaleYName);
        }
        mDefAnim.setInterpolator(new OvershootInterpolator());
        mDefAnim.setDuration(400);
        mDefAnim.start();
    }

    public DNavigationItem setCustomizeAnim(DCustomNavigationItemAnim customizeAnim) {
        this.mDCustomNavigationItemAnim = customizeAnim;
        return this;
    }

    /**
     * icon和name之间的边距
     *
     * @param margin 边距
     */
    public void setIconNameMargin(int margin) {
        mIconNameMargin = margin;
        mItemName.post(new Runnable() {
            @Override
            public void run() {
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) mItemIcon.getLayoutParams();
                layoutParams.setMargins(layoutParams.getMarginStart(), mIconTopMargin, layoutParams.getMarginEnd(), layoutParams.bottomMargin);
            }
        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        isShow = true;
        setRes();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isShow = false;
        if (mDefAnim != null) {
            mDefAnim.cancel();
            mDefAnim = null;
        }
        if (mCustomizeAnim != null) {
            mCustomizeAnim.cancel();
            mCustomizeAnim = null;
        }
    }

    public float getWeight() {
        return weight;
    }

    public DNavigationItem setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    /**
     * 设置字体颜色
     */
    public DNavigationItem setTextColor(int textColor) {
        mItemName.setTextColor(textColor);
        return this;
    }

    /**
     * 设置字体
     *
     * @param fontName setTextTTF(getContext().getResources().getResourceName(R.font.***), Typeface.BOLD)
     * @param style    @param style Typeface.BOLD
     */
    public DNavigationItem setTextTTF(String fontName, int style) {
        Typeface typeface = Typeface.create(fontName, style);
        mItemName.setTypeface(typeface);
        return this;
    }

    public DNavigationItem setNameSelectColor(@ColorRes int color) {
        mItemName.setTextColor(ContextCompat.getColorStateList(getContext(), color));
        return this;
    }

    public TextView getItemNameVIew() {
        return mItemName;
    }

    public ImageView getItemIconView() {
        return mItemIcon;
    }

}
