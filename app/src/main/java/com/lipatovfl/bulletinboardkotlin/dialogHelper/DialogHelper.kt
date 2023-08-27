package com.lipatovfl.bulletinboardkotlin.dialogHelper

import android.app.AlertDialog
import android.view.LayoutInflater
import com.lipatovfl.bulletinboardkotlin.MainActivity
import com.lipatovfl.bulletinboardkotlin.R
import com.lipatovfl.bulletinboardkotlin.databinding.SignDialogBinding

class DialogHelper(private val act: MainActivity) {
    fun createSignDialog(index: Int) {
        val builder = AlertDialog.Builder(act)
        val binDialog = SignDialogBinding.inflate(act.layoutInflater)
        val view = binDialog.root
        if(index == DialogConst.SIGN_UP_STATE) {
            binDialog.tvSignTitle.text = act.resources.getString(R.string.aс_sign_up)
            binDialog.btSignUpIn.text = act.resources.getString(R.string.sign_up_action)
        } else {
            binDialog.tvSignTitle.text = act.resources.getString(R.string.aс_sign_in)
            binDialog.btSignUpIn.text = act.resources.getString(R.string.sign_in_action)
        }
        builder.setView(view)
        builder.show()
    }
}