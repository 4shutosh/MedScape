package com.cse.medscape.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cse.medscape.R
import com.cse.medscape.activities.MyDiagnosisActivity
import com.google.firebase.auth.FirebaseAuth

class SplashFragment : Fragment(R.layout.splash_fragment) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkForUser()
    }

    private fun checkForUser() {
        Handler(Looper.getMainLooper()).postDelayed({
            val auth: FirebaseAuth = FirebaseAuth.getInstance()
            if (auth.currentUser == null) {
                // login
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
            } else {
                // go to main activity
                val intent = Intent(context, MyDiagnosisActivity::class.java)
                activity?.let {
                    it.startActivity(intent)
                    it.finish()
                }
            }
        }, 1500)
    }
}