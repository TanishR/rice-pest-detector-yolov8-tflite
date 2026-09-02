package com.bitmesra_ml.yolov8pestdetection

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainDrawerActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_drawer)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        // ✅ FIND THE TOOLBAR AND SET AS ACTION BAR
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.purple))

        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open,
            R.string.close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.drawerArrowDrawable.color = ContextCompat.getColor(this, R.color.purple) // 👈 hamburger color
        toggle.syncState()
        toolbar.navigationIcon?.setTint(ContextCompat.getColor(this, R.color.purple))



        // ✅ SETUP TOGGLE WITH TOOLBAR
        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        // Load default fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, WelcomeFragment())
            .commit()

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, WelcomeFragment())
                        .commit()
                    drawerLayout.closeDrawers()
                    true
                }
                R.id.nav_pests -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, PestListFragment())
                        .commit()
                    drawerLayout.closeDrawers()
                    true
                }
                else -> false
            }
        }
    }


    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return if (toggle.onOptionsItemSelected(item)) true
        else super.onOptionsItemSelected(item)
    }
}
