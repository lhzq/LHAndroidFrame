package com.jimtrency.lh.lhandroidframe.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017\12\12 0012.
 */

public class MainFraModelImpl {

    public void fetchTwoLevelData(LoadTwoMenuListener menuListener){
        List<String> newTitleList=new ArrayList<>();
        newTitleList.add("新闻");
        newTitleList.add("政法法规");
        newTitleList.add("公告");
        newTitleList.add("案例");
        newTitleList.add("重庆市");

        menuListener.onComplete(newTitleList);
    }

    public interface LoadTwoMenuListener{
        void onComplete( List<String> newTitleList);
    }
}
