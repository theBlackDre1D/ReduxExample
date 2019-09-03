package com.example.beesafeexample.core.base

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.dmoral.toasty.Toasty

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setupUI()
    }

    open fun setupUI() {}

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showErrorToast(message: String) {
        Toasty.error(this, message, Toast.LENGTH_SHORT, true).show()
    }

    fun showSuccessToast(message: String) {
        Toasty.success(this, message, Toast.LENGTH_SHORT, true).show()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) finish()
        else super.onBackPressed()
    }

}