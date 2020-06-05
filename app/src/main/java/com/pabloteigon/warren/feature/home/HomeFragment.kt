package com.pabloteigon.warren.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.pabloteigon.warren.R
import com.pabloteigon.warren.databinding.FragmentHomeBinding
import com.pabloteigon.warren.feature.RxBus
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val args: HomeFragmentArgs by navArgs()
    private lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        email = args.email
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater, R.layout.fragment_home, container, false
        )

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        RxBus.unregister(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tvTitle.text = getString(R.string.home_title, email)
    }
}