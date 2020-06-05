package com.pabloteigon.warren.feature

import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.pabloteigon.warren.R
import com.pabloteigon.warren.data.exception.BadRequestException
import com.pabloteigon.warren.data.exception.ConflictException
import com.pabloteigon.warren.data.exception.UnauthorizedException
import com.pabloteigon.warren.extension.snack
import com.pabloteigon.warren.feature.viewmodel.BaseViewModel
import com.pabloteigon.warren.feature.viewmodel.ViewState
import java.net.UnknownHostException

abstract class AbstractFragment : Fragment() {

    companion object {
        const val TAG = "AbstractFragment"
    }

    abstract val viewModel: BaseViewModel

    protected fun setupErrorState(parentSnackView: View) {
        viewModel.errorState.observe(this, Observer { state ->
            when (state) {
                is ViewState.Failed -> {
                    Log.e(TAG, "Failed " + state.throwable)
                    when (state.throwable) {
                        is UnauthorizedException -> {
                            parentSnackView.snack(message = getString(R.string.app_name), f = {})
                        }
                        is BadRequestException -> {
                            parentSnackView.snack(message = getString(R.string.bad_request), f = {})
                        }
                        is AccessDeniedException -> {
                            parentSnackView.snack(message = getString(R.string.app_name), f = {})
                        }
                        is ConflictException -> {
                            parentSnackView.snack(message = state.throwable.message!!, f = {})
                        }
                        is UnknownHostException -> {
                            parentSnackView.snack(
                                message = getString(R.string.internet_connection_error_msg),
                                f = {})
                        }
                        else -> {
                            parentSnackView.snack(message = state.throwable.message ?: "", f = {})
                        }
                    }
                }
            }
        })
    }

    protected fun setupSuccessState(parentSnackView: View) {
        viewModel.successState.observe(this, Observer { state ->
            when (state) {
                is ViewState.SuccessEmpty -> {
                    Log.e(TAG, "Success $state")

                    parentSnackView.snack(message = getString(R.string.success_msg), f = {})
                }
            }
        })
    }

    override fun onPause() {
        super.onPause()
        RxBus.unregister(this)
    }
}