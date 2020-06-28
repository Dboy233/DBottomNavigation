package com.dboy.bottom.lib.nav;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.ColorRes;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

/**
 * @author 夜斗
 * @date 2020/6/2
 * Class 描述 :
 */
public class DBottomNavigation extends FrameLayout {
    /**
     * 默认底部边距
     */
    private final int DEFAULT_NAV_MARGIN = 24;
    /**
     * item 容器
     */
    private LinearLayoutCompat mItemContainer;
    /**
     * 背景
     */
    private View mBackgroundView;
    /**
     * item列表
     */
    private List<DNavigationItem> mItems;
    /**
     * 点击事件监听器
     */
    private DNavigationItemClickListener mListener;
    /**
     * 设置整体item左右的边距
     */
    private int mNavItemStartEndMargin = 0;
    /**
     * 导航栏底部边距
     */
    private int mNavItemBottomMargin = DEFAULT_NAV_MARGIN;
    /**
     * 背景配置
     */
    private BackgroundConfig mBackgroundConfig;
    /**
     * 顶部内边距
     */
    private int mNavItemTopMargin = 0;

    /**
     * 可以点击
     */
    private boolean mCanClick = true;

    public DBottomNavigation(Context context) {
        super(context);
        init(context);
    }

    public DBottomNavigation(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DBottomNavigation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.layout_bottom_nav, this);
        mBackgroundView = rootView.findViewById(R.id.nav_background);
        mItemContainer = rootView.findViewById(R.id.nav_item_container);
        setClipChildren(false);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        refreshRes();
    }

    private void refreshRes() {
        if (mNavItemBottomMargin == DEFAULT_NAV_MARGIN) {
            mItemContainer.post(new Runnable() {
                @Override
                public void run() {
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) mItemContainer.getLayoutParams();
                    layoutParams.setMargins(layoutParams.getMarginStart(), layoutParams.topMargin, layoutParams.getMarginEnd(), mNavItemBottomMargin);
                    mItemContainer.setLayoutParams(layoutParams);
                }
            });
        }
    }

    /**
     * 设置整体item左右的边距
     *
     * @param navItemStartEndMargin 边距
     */
    public DBottomNavigation setNavItemStartEndMargin(final int navItemStartEndMargin) {
        mNavItemStartEndMargin = navItemStartEndMargin;
        mItemContainer.post(new Runnable() {
            @Override
            public void run() {
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) mItemContainer.getLayoutParams();
                layoutParams.setMargins(mNavItemStartEndMargin, layoutParams.topMargin, mNavItemStartEndMargin, layoutParams.bottomMargin);
                mItemContainer.setLayoutParams(layoutParams);
            }
        });
        return this;
    }

    /**
     * 设置导航栏的子项item
     *
     * @param items 导航项列表
     */
    public DBottomNavigation setItems(List<DNavigationItem> items) {
        mItems = items;
        refreshItem();
        return this;
    }

    /**
     * 设置底部内边距
     *
     * @param navItemBottomMargin 边距大小
     */
    public DBottomNavigation setNavItemBottomMargin(final int navItemBottomMargin) {
        mNavItemBottomMargin = navItemBottomMargin;
        mItemContainer.post(new Runnable() {
            @Override
            public void run() {
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) mItemContainer.getLayoutParams();
                layoutParams.setMargins(layoutParams.getMarginStart(), layoutParams.topMargin, layoutParams.getMarginEnd(), DPUtil.dp2px(mNavItemBottomMargin));
                mItemContainer.setLayoutParams(layoutParams);
            }
        });
        return this;
    }

    /**
     * 设置顶部内边距
     *
     * @param margin 边距大小
     */
    public DBottomNavigation setNavItemTopMargin(final int margin) {
        mNavItemTopMargin = margin;
        mItemContainer.post(new Runnable() {
            @Override
            public void run() {
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) mItemContainer.getLayoutParams();
                layoutParams.setMargins(layoutParams.getMarginStart(), DPUtil.dp2px(mNavItemTopMargin), layoutParams.getMarginEnd(), layoutParams.bottomMargin);
            }
        });
        return this;
    }

    /**
     *  设置item的顶部边距
     * @param margin 边距大小
     */
    public DBottomNavigation setItemTopMargin(int margin) {
        for (DNavigationItem item : mItems) {
            item.setTopMargin(margin);
        }
        return this;
    }

    /**
     * 设置icon 和 name的内边距
     *
     * @param margin 边距
     */
    public DBottomNavigation setIconNameMargin(int margin) {
        for (DNavigationItem item : mItems) {
            item.setIconNameMargin(margin);
        }
        return this;
    }

    /**
     * 设置item点击事件
     *
     * @param listener 回调
     */
    public DBottomNavigation setOnItemClickListener(DNavigationItemClickListener listener) {
        mListener = listener;
        return this;
    }

    /**
     * 设置选中位置
     *
     * @param pos 位置
     */
    public void setSelectedItem(int pos) {
        changeSelected(mItems.get(pos));
    }

    /**
     * 设置自定义点击效果动画
     *
     * @param itemAnim 动画回调
     */
    public DBottomNavigation setCustomItemAnim(DCustomNavigationItemAnim itemAnim) {
        for (DNavigationItem item : mItems) {
            item.setCustomizeAnim(itemAnim);
        }
        return this;
    }

    /**
     * 设置导航栏背景的样式
     *
     * @param config 配置
     */
    public DBottomNavigation setBackgroundViewConfig(final BackgroundConfig config) {
        mBackgroundConfig = config;
        mBackgroundView.setBackgroundResource(mBackgroundConfig.getBackgroundRes());
        mBackgroundView.post(new Runnable() {
            @Override
            public void run() {
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) mBackgroundView.getLayoutParams();
                if (mBackgroundConfig.getBackgroundHeightPercent() != -1) {
                    layoutParams.matchConstraintPercentHeight = mBackgroundConfig.getBackgroundHeightPercent();
                }
                if (mBackgroundConfig.getBackgroundVerticalBias() != -1) {
                    layoutParams.verticalBias = mBackgroundConfig.getBackgroundVerticalBias();
                }
                mBackgroundView.setLayoutParams(layoutParams);
            }
        });
        return this;
    }

    /**
     * 设置字体颜色
     *
     * @param textColor
     * @return
     */
    public DBottomNavigation setTextColor(int textColor) {
        if (mItems != null && mItems.size() != 0) {
            for (DNavigationItem item : mItems) {
                item.setTextColor(textColor);
            }
        }
        return this;
    }

    /**
     * 设置字体
     *
     * @param fontName setTextTTF(getContext().getResources().getResourceName(R.font.***), Typeface.BOLD)
     * @param style    Typeface.BOLD
     */
    public DBottomNavigation setTextTTF(String fontName, int style) {
        if (mItems != null && mItems.size() != 0) {
            for (DNavigationItem item : mItems) {
                item.setTextTTF(fontName, style);
            }
        }
        return this;
    }

    /**
     * 设置字体大小
     *
     * @param sp 单位sp
     */
    public DBottomNavigation setNameTextSize(int sp) {
        if (mItems != null && mItems.size() != 0) {
            for (DNavigationItem item : mItems) {
                item.setNameTextSize(sp);
            }
        }
        return this;
    }

    /**
     *  建立color文件夹，设置color选择器
     * @param selectorColor color选择器文件
     */
    public DBottomNavigation setNameSelectorColor(@ColorRes int selectorColor) {
        if (mItems != null && mItems.size() != 0) {
            for (DNavigationItem item : mItems) {
                item.setNameSelectColor(selectorColor);
            }
        }
        return this;
    }

    /**
     * 刷新item
     */
    private void refreshItem() {
        mItemContainer.removeAllViews();
        for (DNavigationItem item : mItems) {
            item.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeSelected((DNavigationItem) v);
                    if (mListener != null) {
                        if (v instanceof DNavigationItem) {
                            mListener.onClick((DNavigationItem) v, mItems.indexOf(v));
                        }
                    }
                }
            });
            mItemContainer.addView(refreshItemLayoutParam(item));
        }
    }


    @Override
    public void setClickable(boolean clickable) {
        super.setClickable(clickable);
        if (mItems == null) {
            throw new RuntimeException("mItems = null  先调用setItems() 方法");
        }
        for (DNavigationItem dNavigationItem : mItems) {
            dNavigationItem.setClickable(clickable);
        }
    }

    /**
     * 改变选中状态
     */
    private void changeSelected(DNavigationItem item) {
        for (DNavigationItem dNavigationItem : mItems) {
            if (item == dNavigationItem) {
                dNavigationItem.setSelected(true);
            } else {
                dNavigationItem.setSelected(false);
            }
        }
    }

    private DNavigationItem refreshItemLayoutParam(DNavigationItem item) {
        item.setLayoutParams(new LinearLayoutCompat.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, item.getWeight()));
        return item;
    }
}
