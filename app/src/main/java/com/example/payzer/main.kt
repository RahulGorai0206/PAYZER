package com.example.payzer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.payzer.databinding.FragmentMainBinding
import com.google.android.material.textfield.TextInputEditText

class main : Fragment() {


//    private val transactionViewModel by viewModels<ItemViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view: View = inflater.inflate(R.layout.fragment_main, container,false)
        val budget = view.findViewById<TextView>(R.id.budget_text_value)
        budget.text = arguments?.getString("data")

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}