package com.pabloteigon.warren.feature.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.pabloteigon.warren.Constants.HOME_FRAGMENT
import com.pabloteigon.warren.Constants.LOGIN_FRAGMENT
import com.pabloteigon.warren.R
import com.pabloteigon.warren.databinding.FragmentLoginBinding
import com.pabloteigon.warren.extension.snack
import com.pabloteigon.warren.feature.AbstractFragment
import com.pabloteigon.warren.feature.RxBus
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : AbstractFragment() {

    override val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        this.lifecycle.addObserver(viewModel)

        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater, R.layout.fragment_login, container, false
        ).apply {
            vm = viewModel
            lifecycleOwner = this@LoginFragment
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observeEvents()
        setupErrorState(clLogin)

        btnLogin.setOnClickListener {
            when {
                tietEmail.text.isNullOrEmpty() -> {
                    btnLogin.snack(message = getString(R.string.type_your_name), f = {})

                }
                tietPassword.text.isNullOrEmpty() -> {
                    btnLogin.snack(message = getString(R.string.type_your_password), f = {})
                }
                else -> {
                    viewModel.email.value = tietEmail.text.toString()
                    viewModel.password.value = tietPassword.text.toString()
                    viewModel.getAccessToken()
                }
            }
        }
    }

    private fun observeEvents() {
        RxBus.subscribe(LOGIN_FRAGMENT, this, Consumer {
            when (it.toString()) {
                HOME_FRAGMENT -> startHomeFragment()
            }
        })
    }

    private fun startHomeFragment() {
        val directions =
            LoginFragmentDirections.fragmentHome(
                email = viewModel.email.value!!
            )
        findNavController().navigate(directions)
    }
}