package com.lipatovfl.bulletinboardkotlin.account_helper

import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.lipatovfl.bulletinboardkotlin.MainActivity
import com.lipatovfl.bulletinboardkotlin.R

class AccountHelper(private val act: MainActivity) {
    // Функция для регистрации
    fun signUpWithEmail(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            act.mainAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->                    // обьект, который несет в себе информацию, зареган или нет
                    if (task.isSuccessful) {
                        sendEmailVerification(task.result?.user!!)
                    } else {
                        Toast.makeText(
                            act,
                            act.resources.getString(R.string.sign_up_error),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }

    private fun sendEmailVerification(user: FirebaseUser) {
        user.sendEmailVerification().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    act,
                    act.resources.getString(R.string.send_verification_done),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    act,
                    act.resources.getString(R.string.send_verification_error),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}