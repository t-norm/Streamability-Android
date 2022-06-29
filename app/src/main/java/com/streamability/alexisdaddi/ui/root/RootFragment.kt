package com.streamability.alexisdaddi.ui.root

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.streamability.alexisdaddi.databinding.FragmentRootBinding

class RootFragment : Fragment() {
    private var _binding: FragmentRootBinding? = null
    private val binding: FragmentRootBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRootBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}