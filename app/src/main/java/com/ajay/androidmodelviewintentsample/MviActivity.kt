package com.ajay.androidmodelviewintentsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ajay.androidmodelviewintentsample.databinding.ActivityMainBinding

class MviActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(MviViewModel::class.java)

        val actor = MviActor(viewModel::takeAction)

        binding.actor = actor
        binding.notifyPropertyChanged(BR.actor)

        observeData(binding, viewModel)
    }
    private fun observeData(binding: ActivityMainBinding, viewModel: MviViewModel) {
        val contentHandler = MviContentHandler(binding)
        val stateObserver = Observer<MviState?> {
            // null state indicates there is no action needed
            it ?: return@Observer

            // Hide the loading state
            if (it != MviState.Loading) {
                hideLoading()
            }

            when (it) {
                is MviState.Loading -> showLoading()
                is MviState.InvalidNumberError -> {
                    Toast.makeText(this, R.string.msg_invalid_number_selected, Toast.LENGTH_SHORT).show()
                }
                is MviState.Content -> contentHandler.handleContent(it)
            }
        }

        viewModel.effect.observe(this, stateObserver)
        viewModel.state.observe(this, stateObserver)
    }

    /**
     * Show the loading view
     */
    private fun showLoading() = findViewById<LinearLayout>(R.id.loadingView)?.apply { visibility = View.VISIBLE }

    /**
     * Hide the loading view
     */
    private fun hideLoading() = findViewById<LinearLayout>(R.id.loadingView)?.apply { visibility = View.GONE }

}
