package com.example.beesafeexample.Utils

import com.example.beesafeexample.AppController
import com.example.beesafeexample.mainStore
import com.example.beesafeexample.redux.actions.LoginActions
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

object LoginUtils {
    private val auth = FirebaseAuth.getInstance()
    private var smsCode = ""
    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(exception: FirebaseException) {
            mainStore.dispatch(LoginActions.LoginFailed(exception.message!!))
        }

        override fun onCodeSent(verificationId: String, code: PhoneAuthProvider.ForceResendingToken) {
            smsCode = verificationId
            mainStore.dispatch(LoginActions.WaitingForCode())
        }
    }

    fun setPhoneAuthentification(number: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            number,
            60,
            TimeUnit.SECONDS,
            AppController.currentActivity.get()!!,
            callbacks
        )
    }

    fun verifySMSCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(smsCode, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                mainStore.dispatch(LoginActions.LoginCompleted(""))
            } else {
                mainStore.dispatch(LoginActions.LoginFailed("Verification not successful"))
            }
        }
    }
}