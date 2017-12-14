package com.jimtrency.lh.lhandroidframe.view;

import com.jimtrency.lh.androidframe.view.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2017\12\12 0012.
 */

public interface IMainFragmentView extends IBaseView {

    //显示出横向滚动二级菜单
    void secondLevelMenu(List<String> newTitleList);
}
