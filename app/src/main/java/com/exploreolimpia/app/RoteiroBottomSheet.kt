package com.exploreolimpia.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RoteiroBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_roteiro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnClose: FrameLayout = view.findViewById(R.id.btnCloseRoteiro)
        val btnOk: Button = view.findViewById(R.id.btnOkRoteiro)

        btnClose.setOnClickListener {
            dismiss()
        }

        btnOk.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Roteiro de Olímpia salvo com sucesso! Boa viagem!",
                Toast.LENGTH_SHORT
            ).show()
            dismiss()
        }
    }
}
