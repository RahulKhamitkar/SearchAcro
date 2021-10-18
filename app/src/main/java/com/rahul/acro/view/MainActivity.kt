package com.rahul.acro.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahul.acro.R
import com.rahul.acro.adapter.AcroListAdapter
import com.rahul.acro.databinding.ActivityMainBinding
import com.rahul.acro.utils.NetworkConnection
import com.rahul.acro.viewmodel.AcroViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var mAcroListAdapter: AcroListAdapter
    lateinit var viewModel: AcroViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mAcroListAdapter = AcroListAdapter(arrayListOf(), this)
        viewModel = ViewModelProvider(this).get(AcroViewModel::class.java)

        binding.rcView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAcroListAdapter
        }

        binding.etShortForm.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.isNullOrBlank()) {
                    binding.rcView.visibility = View.GONE
                }
                if (p0?.length!! > 2) {
                    binding.rcView.visibility = View.VISIBLE
                    binding.tvNoData.visibility = View.GONE
                    NetworkConnection.isNetworkAvailable(applicationContext)
                    viewModel.findAcro(p0.toString())
                    observeList()
                }
            }

        })

        binding.btnSearch.setOnClickListener {
            val textFind: String = binding.etShortForm.text.toString()
            if (!textFind.isNullOrEmpty()) {
                NetworkConnection.isNetworkAvailable(this)
                viewModel.findAcro(textFind)
                observeList()
            } else {
                Toast.makeText(this, R.string.plz_enter_the_text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeList() {
        viewModel.response.observe(this, Observer {

            if (!it.isNullOrEmpty()) {
                mAcroListAdapter.updateMyCartList(it[0].lfs)
                binding.rcView.visibility = View.VISIBLE
                binding.tvNoData.visibility = View.GONE
            } else {
                binding.tvNoData.visibility = View.VISIBLE
                binding.rcView.visibility = View.GONE
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }

        })
        viewModel.loadingError.observe(this, Observer { isError ->
            isError?.let {
                binding.tvErrorMsg.visibility = if (it) View.VISIBLE else View.GONE
            }

        })
    }
}