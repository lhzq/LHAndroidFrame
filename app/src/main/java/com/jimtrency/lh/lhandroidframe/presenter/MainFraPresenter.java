package com.jimtrency.lh.lhandroidframe.presenter;

import com.jimtrency.lh.androidframe.presenter.BasePresenter;
import com.jimtrency.lh.lhandroidframe.model.MainFraModelImpl;
import com.jimtrency.lh.lhandroidframe.view.IMainFragmentView;

import java.util.List;

/**
 * Created by Administrator on 2017\12\12 0012.
 */

public class MainFraPresenter extends BasePresenter<IMainFragmentView>{

    private IMainFragmentView iMainFragmentView;
    private MainFraModelImpl mainFraModel;

    public MainFraPresenter(IMainFragmentView iMainFragmentView){
        this.iMainFragmentView=iMainFragmentView;
        mainFraModel=new MainFraModelImpl();
    }

    public void fetchTwoMenuData(){
        mainFraModel.fetchTwoLevelData(new MainFraModelImpl.LoadTwoMenuListener() {
            @Override
            public void onComplete(List<String> newTitleList) {
                iMainFragmentView.secondLevelMenu(newTitleList);
            }
        });
    }
}
