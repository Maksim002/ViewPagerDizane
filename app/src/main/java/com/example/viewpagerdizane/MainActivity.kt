package com.example.viewpagerdizane


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.example.viewpagerdizane.fragment.MyApplicationFragment
import com.example.viewpagerdizane.fragment.MyOperationFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.view.animation.*

class MainActivity : AppCompatActivity() {
    private var numberBar = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPager()
    }

    private fun initPager() {
        val adapter = ProfilePagerAdapter(this.supportFragmentManager)
        adapter.addFragment(MyOperationFragment(), "Мои операции")
        adapter.addFragment(MyApplicationFragment(), "Мои заявки")
        profile_pager.adapter = adapter

        profile_pager.isEnabled = false

        v1.setOnClickListener {
            profile_pager.currentItem = 0
        }

        v2.setOnClickListener {
            profile_pager.currentItem = 1
        }

        profile_bar_zero.visibility = View.VISIBLE
        profile_bar_one.visibility = View.GONE

        profile_pager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                numberBar = position
            }

            override fun onPageSelected(position: Int) {
                val fadeIn = AnimationUtils.loadAnimation(this@MainActivity, R.anim.fade_in)
                val fadeOut = AnimationUtils.loadAnimation(this@MainActivity, R.anim.fade_out)
                if (position != 1) {
                    profile_bar_zero.startAnimation(fadeIn)
                    profile_bar_one.startAnimation(fadeOut)
                    profile_bar_zero.visibility = View.VISIBLE
                    profile_bar_one.visibility = View.GONE
                } else {
                    profile_bar_one.startAnimation(fadeIn)
                    profile_bar_zero.startAnimation(fadeOut)
                    profile_bar_one.visibility = View.VISIBLE
                    profile_bar_zero.visibility = View.GONE
                }
            }
            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
}