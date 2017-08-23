package dev.teerayut.pml.main;


import dev.teerayut.pml.base.BaseMvpInterface;

/**
 * Created by teerayut.k on 7/24/2017.
 */

public class MainInterface {

    public interface view extends BaseMvpInterface.View {
    }

    public interface presenter extends BaseMvpInterface.Presenter<view> {

    }
}
