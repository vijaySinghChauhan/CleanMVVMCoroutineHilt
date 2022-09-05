package com.vjchauhan.cleanmvvmcoroutinehilt.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vjchauhan.cleanmvvmcoroutinehilt.R


class BottomSheet : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(
            R.layout.bottom_sheet_layout,
            container, false
        )
        val algo_button = v.findViewById<TextView>(R.id.algo_button)
        algo_button.setOnClickListener {
            Toast.makeText(
                activity,
                "Algorithm Shared", Toast.LENGTH_SHORT
            )
                .show()
            dismiss()
        }
        return v
    }
}
