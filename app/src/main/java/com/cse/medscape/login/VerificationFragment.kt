package com.cse.medscape.login

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cse.medscape.R
import com.cse.medscape.activities.MyDiagnosisActivity
import com.cse.medscape.databinding.FragmentVerificationBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class VerificationFragment : Fragment(R.layout.fragment_verification) {

    private lateinit var binding: FragmentVerificationBinding

    private lateinit var storedVerificationId: String
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private val TAG = "VerificationFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVerificationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progress.visibility = View.VISIBLE
        val phoneNumber = arguments?.getString("phone").toString()
        firebaseLogin(phoneNumber)
        Log.d(TAG, "onViewCreated: $phoneNumber")


        binding.verifyButton.setOnClickListener {
            binding.progress.visibility = View.VISIBLE
            if (binding.otpEt.text.isNullOrBlank()) {
                binding.progress.visibility = View.GONE
                binding.otpEt.error = getString(R.string.pleaseEnterOTP)
                return@setOnClickListener
            } else {
                val code = binding.otpEt.text.toString()
                val credential = PhoneAuthProvider.getCredential(storedVerificationId, code)
                signInWithPhoneAuthCredential(credential)
            }
        }


    }

    private fun firebaseLogin(phone: String) {

        val text: String = getString(R.string.otpSentTo) + " " + phone +" ";
        val ss = SpannableString(text)
        val bcsYellow = ForegroundColorSpan(resources.getColor(R.color.progressColor))
        ss.setSpan(bcsYellow, 11, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.phoneText.text = ss
        sendOtp(phone)

    }

    private fun sendOtp(phone: String) {

        val auth = FirebaseAuth.getInstance()
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phone)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    //
    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Log.d(TAG, "onVerificationCompleted:$credential")

            val code = credential.smsCode.toString()
            if (code.isNotEmpty()) {
                binding.otpEt.setText(code)
            }

            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Log.w(TAG, "onVerificationFailed", e)

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
//                Log.d(TAG, "onVerificationFailed: ")
//                Toast.makeText(context, "Wrong otp entered", Toast.LENGTH_SHORT).show()
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                Toast.makeText(context, e.message.toString(), Toast.LENGTH_SHORT).show()
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            Log.d(TAG, "onCodeSent:$verificationId")
            binding.progress.visibility = View.GONE
            storedVerificationId = verificationId
            resendToken = token
        }

        override fun onCodeAutoRetrievalTimeOut(p0: String) {
            super.onCodeAutoRetrievalTimeOut(p0)
            sendOtp(arguments?.getString("phone").toString())
        }
    }

    //
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        val auth = FirebaseAuth.getInstance()
        activity?.let { it ->
            auth.signInWithCredential(credential)
                .addOnCompleteListener(it) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")
                        val user = task.result?.user

                        binding.progress.visibility = View.GONE

                        // add a user check here
                        val intent = Intent(context, MyDiagnosisActivity::class.java)
                        it.startActivity(intent)
                        it.finish()

                    } else {
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                            Toast.makeText(context, "Wrong OTP", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }
    }
}