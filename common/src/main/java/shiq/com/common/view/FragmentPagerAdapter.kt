package shiq.com.common.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import shiq.com.common.base.ViewPagerBaseFragment

/**
 * created by shi on 2019/6/13/013
 *
 */
class FragmentPagerAdapter(fm: FragmentManager, var pagerList: MutableList<ViewPagerBaseFragment>) :
    FragmentStatePagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return pagerList[position]
    }

    override fun getCount(): Int {
        return pagerList.size
    }

    override fun getItemPosition(obj: Any): Int {

        return POSITION_NONE

    }

}