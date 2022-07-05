package com.streamability.alexisdaddi.ui.root

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.streamability.alexisdaddi.databinding.FragmentRootBinding
import com.streamability.datalayer.domain.models.sharedPref.Login
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RootFragment : Fragment() {
    private var _binding: FragmentRootBinding? = null
    private val binding: FragmentRootBinding get() = _binding!!

    private var user = Login("", "")

    private val viewModel by viewModels<RootViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRootBinding.inflate(inflater, container, false).also {
        _binding = it
        viewModel.getUser()
        getUser()
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val handler = Handler(Looper.getMainLooper())
        binding.animationView.playAnimation()
        handler.postDelayed({
            goToStreamingService()
        }, 4500)

        /***
         * revisit when we have a cloud service
         * set up to receieve api calls then
         ***/
//        fragmentLayoutWhite()
//        val handler2 = Handler(Looper.getMainLooper())
//        handler2.postDelayed({
//            if (user.username != "" && user.username.isNotEmpty()) {
//                goToStreamingService()
//            } else {
//                fragmentLayoutBlack()
//                binding.animationView.playAnimation()
//                val handler = Handler(Looper.getMainLooper())
//                handler.postDelayed({
//                    if (user.username.isEmpty()) {
//                        goToLogin()
//                    } else {
//                        goToStreamingService()
//                    }
//                }, 4700)
//            }
//
//        }, 300)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

//    fun fragmentLayoutWhite() = with(binding) {
//        rootLayout.setBackgroundColor(Color.WHITE)
//        title.visibility = View.GONE
//        subtitle1.visibility = View.GONE
//        subtitle2.visibility = View.GONE
//    }
//
//    fun fragmentLayoutBlack() = with(binding) {
//        rootLayout.setBackgroundColor(Color.BLACK)
//        title.visibility = View.VISIBLE
//        subtitle1.visibility = View.VISIBLE
//        subtitle2.visibility = View.VISIBLE
//    }

    private fun goToStreamingService() {
        findNavController().navigate(RootFragmentDirections.actionRootFragmentToNavGraph())
    }
//
//    private fun goToLogin() {
//        findNavController().navigate(RootFragmentDirections.actionRootFragmentToLoginNavGraph())
//    }

    private fun getUser() {
        lifecycleScope.launch {
            viewModel.user.collect { currentUser ->
                user = currentUser
            }
        }
    }

}