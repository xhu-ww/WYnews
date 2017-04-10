package com.xykj.demo.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.xykj.demo.R;
import com.xykj.demo.base.BaseActivity;
import com.xykj.demo.fragment.MainHomeFragment;
import com.xykj.demo.fragment.MainNewsFragment;
import com.xykj.demo.fragment.MainPersonFragment;
import com.xykj.demo.fragment.MainPictureFragment;
import com.xykj.demo.fragment.MainVideoFragment;


public class MainActivity extends BaseActivity {

    RadioGroup mRgBottomMenu;
    //数组 存储Fragment
    Fragment[] mFragments;
    //当前Fragent的下标
    private int currentIndex;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }
    @Override
    protected void initFindViewById() {
        mRgBottomMenu = (RadioGroup) findViewById(R.id.rg_bottom_menu);
    }
    @Override
    protected void initViews() {
        //将Fragment加入数组
        mFragments = new Fragment[] {
                //主页、新闻、图片、视频、个人
                new MainHomeFragment(),
                new MainNewsFragment(),
                new MainPictureFragment(),
                new MainVideoFragment(),
                new MainPersonFragment()
        };
        //开启事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //设置为默认界面 MainHomeFragment
        ft.add(R.id.main_content,mFragments[0]).commit();
        //RadioGroup选中事件监听 改变fragment
        mRgBottomMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        setIndexSelected(0);
                        break;
                    case R.id.rb_news:
                        setIndexSelected(1);
                        break;
                    case R.id.rb_picture:
                        setIndexSelected(2);
                        break;
                    case R.id.rb_movie:
                        setIndexSelected(3);
                        break;
                    case R.id.rb_person:
                        setIndexSelected(4);
                        break;
                }
            }
        });
    }
    //设置Fragment页面
    private void setIndexSelected(int index) {
        if (currentIndex == index) {
            return;
        }
        //开启事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //隐藏当前Fragment
        ft.hide(mFragments[currentIndex]);
        //判断Fragment是否已经添加
        if (!mFragments[index].isAdded()) {
            ft.add(R.id.main_content,mFragments[index]).show(mFragments[index]);
        }else {
            //显示新的Fragment
            ft.show(mFragments[index]);
        }
        ft.commit();
        currentIndex = index;
    }
}
