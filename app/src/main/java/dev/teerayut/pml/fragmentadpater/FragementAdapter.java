package dev.teerayut.pml.fragmentadpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import dev.teerayut.pml.matchs.RoundsFragment;
import dev.teerayut.pml.table.TableStandingFragment;


/**
 * Created by teerayut on 12/17/15 AD.
 */
public class FragementAdapter extends FragmentStatePagerAdapter {

    private final int PAGE_NUM = 2;

    public FragementAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 1) {
            return new RoundsFragment();
        }else if(position == 0) {
            return new TableStandingFragment();
        } /*else if (position == 2) {
            return new RecentMatchFragment();
        }*/

        return null;
    }

    @Override
    public int getCount() {
        return PAGE_NUM;
    }
}