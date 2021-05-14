package com.cse.medscape.activities

import android.Manifest.permission.CALL_PHONE
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.cse.medscape.R
import com.cse.medscape.database.RemedyFragment
import com.cse.medscape.fragment.*
import com.cse.medscape.viewmodel.UserViewModel
import com.facebook.stetho.Stetho
import com.google.android.material.navigation.NavigationView

class MyDiagnosisActivity : AppCompatActivity() {

    private var mDrawer: DrawerLayout? = null
    private var toolbar: Toolbar? = null
    private lateinit var userViewModel: UserViewModel

    private lateinit var linearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symtoms)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerToggle = setupDrawerToggle()

        // check for permission
        if (!checkForPermission(CALL_PHONE)) {
            ActivityCompat.requestPermissions(this, listOf(CALL_PHONE).toTypedArray(), 100)
        }
        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer?.addDrawerListener(drawerToggle)

        // Find our drawer view
        mDrawer = findViewById(R.id.drawer_layout)

        val nvDrawer = findViewById<NavigationView>(R.id.nvView)

        // Setup drawer view
        setupDrawerContent(nvDrawer)

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        Stetho.initializeWithDefaults(this)

        linearLayout = findViewById(R.id.homeScreen)
        linearLayout.visibility = View.VISIBLE

        // UI elements here
        val userNameInput = findViewById<EditText>(R.id.userName)

        val userAgeInput = findViewById<EditText>(R.id.userAgeInput)

        val userGenderInput = findViewById<EditText>(R.id.userGenderInput)

        val userNameTextView = findViewById<TextView>(R.id.userNameTextView)

        val submitButton = findViewById<Button>(R.id.submitUserInfoBtn)


        if (userViewModel.getUserName(this) == "") {



            submitButton.setOnClickListener {

                val userName = userNameInput.editableText.toString()

                val userAge = userAgeInput.editableText.toString().toInt()

                val userGender = userGenderInput.editableText.toString()

                userViewModel.saveUserInfo(this, userName, userAge, userGender)

                submitButton.visibility = View.GONE
                userNameInput.visibility = View.GONE
                userAgeInput.visibility = View.GONE
                userGenderInput.visibility = View.GONE

                userNameTextView.visibility = View.VISIBLE
                userNameTextView.text = "Welcome ${userViewModel.getUserName(this)}"

            }


        } else {
            userNameTextView.visibility = View.VISIBLE
            userNameTextView.text = "Welcome ${userViewModel.getUserName(this)}"

            userNameInput.visibility = View.GONE
            userAgeInput.visibility = View.GONE
            userGenderInput.visibility = View.GONE
            submitButton.visibility = View.GONE
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults.isNotEmpty()) {
            val bool = grantResults[0] == PackageManager.PERMISSION_GRANTED
            if (bool) {
                // cool
            } else {
                // again ask for permission
//            Toast.makeText(this, "App Won't Work without Permissions!", Toast.LENGTH_SHORT).show()
                ActivityCompat.requestPermissions(this, listOf(CALL_PHONE).toTypedArray(), 100)

            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // The action bar home/up action should open or close the drawer.
        if (setupDrawerToggle().onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            selectDrawerItem(menuItem)
            true
        }
    }

    fun selectDrawerItem(menuItem: MenuItem) {

        linearLayout.visibility = View.GONE
        // Create a new com.cse.medscape.fragment and specify the com.cse.medscape.fragment to show based on nav item clicked
        var fragment: Fragment? = null
        var doctor = 0
        val fragmentClass: Class<*>
        when (menuItem.itemId) {
            R.id.nav_first_fragment -> fragmentClass = FirstFragment::class.java
            R.id.nav_second_fragment -> fragmentClass = SecondFragment::class.java
            R.id.nav_third_fragment -> fragmentClass = ThirdFragment::class.java
            R.id.nav_mentions -> fragmentClass = NlpFragment::class.java
            R.id.nav_diagnosis -> fragmentClass = DiagnosisFragment::class.java
            R.id.nav_feedback -> fragmentClass = WebViewFragment::class.java
            R.id.nav_remedies -> fragmentClass = RemedyFragment::class.java
            R.id.nav_doctor -> fragmentClass = DoctorFragment::class.java
            R.id.nav_ambulance -> {
                fragmentClass = DoctorFragment::class.java
                val intent =
                    Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "102"))
                startActivity(intent)
            }
            R.id.nav_indiaPod -> {
                fragmentClass = WebViewFragment::class.java
                doctor = 1
            }
            else -> fragmentClass = FirstFragment::class.java
        }

        try {
            fragment = fragmentClass.newInstance() as Fragment
            if (fragmentClass == WebViewFragment::class.java) {
                if (doctor == 0) {
                    val temp = Bundle()
                    val link = getString(R.string.feedBackLink)
                    temp.putString("link", link)
                    fragment.arguments = temp
                } else {
                    val temp = Bundle()
                    val link = getString(R.string.indiaPod)
                    temp.putString("link", link)
                    fragment.arguments = temp
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Insert the com.cse.medscape.fragment by replacing any existing com.cse.medscape.fragment
        val fragmentManager = supportFragmentManager

        if (fragment != null) {
            // check back stack here
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment)
                .addToBackStack("Name").commit()
            Log.d("temp", "selectDrawerItem: " + fragmentManager.backStackEntryCount)
        }

        // Highlight the selected item has been done by NavigationView
        menuItem.isChecked = true
        // Set action bar title
        title = menuItem.title
        // Close the navigation drawer
        mDrawer?.closeDrawers()
    }

    private fun setupDrawerToggle(): ActionBarDrawerToggle {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return ActionBarDrawerToggle(
            this,
            mDrawer,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        // Sync the toggle state after onRestoreInstanceState has occurred.
        setupDrawerToggle().syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Pass any configuration change to the drawer toggles
        setupDrawerToggle().onConfigurationChanged(newConfig)
    }

    // fix this
    override fun onBackPressed() {
        mDrawer?.closeDrawers()
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
            if (supportFragmentManager.backStackEntryCount == 0) {
                linearLayout.visibility = View.VISIBLE
                toolbar?.setTitle(R.string.app_name)
            }
        } else {
            super.onBackPressed()
        }
    }

    private fun checkForPermission(permission: String): Boolean {
        ActivityCompat.checkSelfPermission(this, permission)
        return ContextCompat.checkSelfPermission(
            applicationContext,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }
}
