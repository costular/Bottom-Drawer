package com.costular.bottomdrawerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.costular.bottomdrawer.BottomDrawer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            BottomDrawer.createAndShow(supportFragmentManager) {
                theme = R.style.NavigationTheme
                drawerItem {
                    stringRes = R.string.nav_home
                    drawableRes = R.drawable.ic_home_black_24dp
                }
                drawerItem {
                    stringRes = R.string.nav_profile
                    drawableRes = R.drawable.ic_profile
                }
                drawerItem {
                    stringRes = R.string.nav_settings
                    drawableRes = R.drawable.ic_settings
                }
                clickListener = { drawerItem, position ->
                    true
                }
            }
        }
    }
}
