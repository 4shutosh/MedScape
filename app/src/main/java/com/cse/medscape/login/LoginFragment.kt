package com.cse.medscape.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cse.medscape.R
import com.cse.medscape.databinding.FragmentLoginBinding
import com.cse.medscape.viewmodel.LoginViewModel
import com.google.firebase.auth.PhoneAuthProvider


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var loginBinding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    companion object {
        private const val TAG = "LoginFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginBinding = FragmentLoginBinding.inflate(inflater)
        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginBinding.loginButton.setOnClickListener {
            val string = loginBinding.userPhoneLogin.text.toString()
            val bool = viewModel.validatePhone(string)

            if (!bool) {
                // proceed firebase login here
                loginBinding.userPhoneLogin.error = getString(R.string.enterValidPhone)
            } else {
                loginBinding.userPhoneLogin.error = ""
                firebaseLogin(string)
            }
        }
    }

    private fun firebaseLogin(phone: String) {
        val fullPhone =
            loginBinding.countryCodePicker.selectedCountryCodeWithPlus.toString() + phone

        val bundle = Bundle()
        bundle.putString("phone", fullPhone)
        val action = LoginFragmentDirections.actionLoginFragmentToVerificationFragment(fullPhone)
        findNavController().navigate(action)
    }

}