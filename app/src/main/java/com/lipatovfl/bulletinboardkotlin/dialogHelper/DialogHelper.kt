package com.lipatovfl.bulletinboardkotlin.dialogHelper

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.lipatovfl.bulletinboardkotlin.MainActivity
import com.lipatovfl.bulletinboardkotlin.R
import com.lipatovfl.bulletinboardkotlin.account_helper.AccountHelper
import com.lipatovfl.bulletinboardkotlin.databinding.SignDialogBinding

class DialogHelper(private val act: MainActivity) {
    private val accHelper = AccountHelper(act)
    fun createSignDialog(index: Int) {
        val builder = AlertDialog.Builder(act) // Запускаем диалог
        val binDialog = SignDialogBinding.inflate(act.layoutInflater)
        builder.setView(binDialog.root)

        setDialogState(index, binDialog)
        val dialog = builder.create()
        binDialog.btSignUpIn.setOnClickListener {
            setOnClickSignUpIn(index, binDialog, dialog)
        }
        binDialog.btForgetPassword.setOnClickListener {
            setOnClickResetPassword(binDialog, dialog)
        }
        dialog.show()
    }

    private fun setOnClickResetPassword(binDialog: SignDialogBinding, dialog: AlertDialog?) {
        if (binDialog.edSignEmail.text.isNotEmpty()) {
            act.mainAuth.sendPasswordResetEmail(binDialog.edSignEmail.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            act,
                            R.string.email_reset_password_was_sent,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            dialog?.dismiss()
        } else {
            binDialog.tvDialogMessage.visibility = View.VISIBLE
        }
    }

    private fun setOnClickSignUpIn(index: Int, binDialog: SignDialogBinding, dialog: AlertDialog?) {
        dialog?.dismiss()
        if (index == DialogConst.SIGN_UP_STATE) {
            accHelper.signUpWithEmail(
                binDialog.edSignEmail.text.toString(),
                binDialog.edSignPassword.text.toString()
            )
        } else {
            accHelper.signInWithEmail(
                binDialog.edSignEmail.text.toString(),
                binDialog.edSignPassword.text.toString()
            )
        }
    }

    private fun setDialogState(index: Int, binDialog: SignDialogBinding) {
        if (index == DialogConst.SIGN_UP_STATE) {
            binDialog.tvSignTitle.text = act.resources.getString(R.string.aс_sign_up)
            binDialog.btSignUpIn.text = act.resources.getString(R.string.sign_up_action)
        } else {
            binDialog.tvSignTitle.text = act.resources.getString(R.string.aс_sign_in)
            binDialog.btSignUpIn.text = act.resources.getString(R.string.sign_in_action)
            binDialog.btForgetPassword.visibility = View.VISIBLE
        }
    }
}