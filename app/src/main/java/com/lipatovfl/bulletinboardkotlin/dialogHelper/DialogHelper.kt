package com.lipatovfl.bulletinboardkotlin.dialogHelper

import android.app.AlertDialog
import android.view.LayoutInflater
import com.lipatovfl.bulletinboardkotlin.MainActivity
import com.lipatovfl.bulletinboardkotlin.databinding.SignDialogBinding

class DialogHelper(private val act: MainActivity) {
    fun createSignDialog() {
        val builder = AlertDialog.Builder(act)
        val binDialog = SignDialogBinding.inflate(act.layoutInflater)
        val view = binDialog.root
        builder.setView(view)
        builder.show()
    }
}