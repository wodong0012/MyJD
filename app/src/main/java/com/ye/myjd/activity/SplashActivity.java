package com.ye.myjd.activity;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.ye.myjd.R;
import com.ye.myjd.util.ActivityUtils;

/**
 * @author : WoDong
 * @date : 2020/3/15 17:01
 * @desc :
 */
public class SplashActivity extends BaseActivity {

    private ImageView mLogo_iv;
    private AlphaAnimation mAlpha;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initAnimation();
        initEvent();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_splash);
        mLogo_iv = findViewById(R.id.logo_iv);
    }

    @Override
    protected void initEvent() {
        mAlpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束后回调
                ActivityUtils.startActivity(SplashActivity.this, LoginActivity.class, true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    protected void initAnimation() {
        mAlpha = new AlphaAnimation(0.2f, 1.0f);
        mAlpha.setDuration(3000);
        mAlpha.setFillAfter(true);
        mLogo_iv.setAnimation(mAlpha);
    }
}
