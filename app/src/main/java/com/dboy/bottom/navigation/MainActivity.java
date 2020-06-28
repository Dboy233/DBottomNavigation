package com.dboy.bottom.navigation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dboy.bottom.lib.nav.BackgroundConfig;
import com.dboy.bottom.lib.nav.DBottomNavigation;
import com.dboy.bottom.lib.nav.DCustomNavigationItemAnim;
import com.dboy.bottom.lib.nav.DNavItemConfig;
import com.dboy.bottom.lib.nav.DNavigationItem;
import com.dboy.bottom.lib.nav.DNavigationItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DNavigationItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initOne();
        initTwo();
        initThree();
        initFour();
    }

    /**
     * 普通的使用
     */
    private void initOne() {
        List<DNavigationItem> mDNavigationItems = new ArrayList<>();
        mDNavigationItems.add(new DNavigationItem(this, new DNavItemConfig(R.drawable.selector_tab_home, "首页"), true));
        mDNavigationItems.add(new DNavigationItem(this, new DNavItemConfig(R.drawable.selector_tab_my, "我的")));
        mDNavigationItems.add(new DNavigationItem(this, new DNavItemConfig(R.drawable.selector_tab_redeem, "礼物")));
        mDNavigationItems.add(new DNavigationItem(this, new DNavItemConfig(R.drawable.selector_tab_invite, "邀请")));
        DBottomNavigation navigation = findViewById(R.id.mainNav);
        navigation.setItems(mDNavigationItems)
                .setIconNameMargin(8)
                .setNavItemBottomMargin(8)
                .setNavItemStartEndMargin(20)
                .setBackgroundViewConfig(new BackgroundConfig(R.drawable.shape_nav_bg2));
        navigation.setOnItemClickListener(this);
    }

    /**
     * 突出项演示
     */
    private void initTwo() {
        List<DNavigationItem> mDNavigationItems = new ArrayList<>();
        mDNavigationItems.add(new DNavigationItem(this, new DNavItemConfig(R.drawable.selector_tab_my, "我的")));
        mDNavigationItems.add(new DNavigationItem(this, new DNavItemConfig(R.drawable.selector_tab_redeem, "礼物")));
        //设置中间item缩放，和占比 突出显示
        mDNavigationItems.add(new DNavigationItem(this, new DNavItemConfig(R.drawable.selector_tab_home, "首页").setZoomSize(1.8f), true, 1.2f));
        mDNavigationItems.add(new DNavigationItem(this, new DNavItemConfig(R.drawable.selector_tab_invite, "邀请")));
        mDNavigationItems.add(new DNavigationItem(this, new DNavItemConfig(R.drawable.selector_tab_lead, "排行榜")));
        DBottomNavigation navigation = findViewById(R.id.mainNav1);
        navigation.setItems(mDNavigationItems)
                .setIconNameMargin(8)
                .setNavItemBottomMargin(8)
                .setNavItemStartEndMargin(20)
                .setBackgroundViewConfig(new BackgroundConfig(R.drawable.shape_nav_bg).setBackgroundHeightPercent(0.8f));
        navigation.setOnItemClickListener(this);
    }

    /**
     * 自定义动画
     */
    private void initThree() {
        List<DNavigationItem> mDNavigationItems = new ArrayList<>();
        mDNavigationItems.add(new DNavigationItem(this, new DNavItemConfig(R.drawable.selector_tab_home, "首页"), true));
        mDNavigationItems.add(new DNavigationItem(this, new DNavItemConfig(R.drawable.selector_tab_my, "我的")));
        mDNavigationItems.add(new DNavigationItem(this, new DNavItemConfig(R.drawable.selector_tab_redeem, "礼物")));
        mDNavigationItems.add(new DNavigationItem(this, new DNavItemConfig(R.drawable.selector_tab_invite, "邀请")));
        DBottomNavigation navigation = findViewById(R.id.mainNav2);
        navigation.setItems(mDNavigationItems)
                .setIconNameMargin(8)
                .setNavItemBottomMargin(8)
                .setNavItemStartEndMargin(20)
                .setBackgroundViewConfig(new BackgroundConfig(R.drawable.shape_nav_bg2));
        navigation.setOnItemClickListener(this);
        navigation.setCustomItemAnim(new DCustomNavigationItemAnim() {
            @Override
            public AnimatorSet setAnim(View icon, View name, boolean isSelected) {
                if (isSelected) {
                    AnimatorSet animatorSet = new AnimatorSet();
                    ObjectAnimator animatorIcon = ObjectAnimator.ofFloat(icon, View.ROTATION, 0f, 25f, 0);
                    ObjectAnimator animatorName = ObjectAnimator.ofFloat(name, View.ROTATION, 0f, -25f, 0);
                    animatorSet.playTogether(animatorName, animatorIcon);
                    animatorSet.setInterpolator(new OvershootInterpolator(2));
                    animatorSet.setDuration(1000);
                    return animatorSet;
                } else {
                    AnimatorSet animatorSet = new AnimatorSet();
                    ObjectAnimator animatorIcon = ObjectAnimator.ofFloat(icon, View.ROTATION, 0f, -25f, 0);
                    ObjectAnimator animatorName = ObjectAnimator.ofFloat(name, View.ROTATION, 0f, 25f, 0);
                    animatorSet.playTogether(animatorName, animatorIcon);
                    animatorSet.setInterpolator(new OvershootInterpolator(2));
                    animatorSet.setDuration(1000);
                    return animatorSet;
                }
            }
        });
    }

    /**
     * 自定义字体 和大小
     */
    private void initFour() {
        List<DNavigationItem> mDNavigationItems = new ArrayList<>();
        mDNavigationItems.add(new DNavigationItem(this, new DNavItemConfig(R.drawable.selector_tab_home, "首页"), true));
        mDNavigationItems.add(new DNavigationItem(this, new DNavItemConfig(R.drawable.selector_tab_my, "我的")));
        mDNavigationItems.add(new DNavigationItem(this, new DNavItemConfig(R.drawable.selector_tab_redeem, "礼物")));
        mDNavigationItems.add(new DNavigationItem(this, new DNavItemConfig(R.drawable.selector_tab_invite, "邀请")));
        DBottomNavigation navigation = findViewById(R.id.mainNav3);
        navigation.setItems(mDNavigationItems)
                .setIconNameMargin(8)
                .setNavItemBottomMargin(8)
                .setNameTextSize(16)//设置大小
                .setNameSelectorColor(R.color.text_selector)//设置选中颜色选择器，当然也可以再自定义动画中进行修改
                .setTextTTF(getResources().getResourceName(R.font.coresans_extrabold), Typeface.BOLD)//修改字体
                .setBackgroundViewConfig(new BackgroundConfig(R.drawable.shape_nav_bg2));
        navigation.setOnItemClickListener(this);
    }

    @Override
    public void onClick(DNavigationItem view, int pos) {
        Toast.makeText(this, " pos  " + pos, Toast.LENGTH_SHORT).show();
    }
}