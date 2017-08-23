package dev.teerayut.pml.main;


import dev.teerayut.pml.base.BaseMvpPresenter;

/**
 * Created by teerayut.k on 7/24/2017.
 */

public class MainPresenter extends BaseMvpPresenter<MainInterface.view> implements MainInterface.presenter {

    public static MainInterface.presenter create() {
        return new MainPresenter();
    }
}
