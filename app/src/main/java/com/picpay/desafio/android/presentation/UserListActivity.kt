package com.picpay.desafio.android.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var adapter: UserListAdapter
    private lateinit var binding: ActivityMainBinding

    private val viewModel: UserListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupObservers()
        viewModel.getUsers()
    }

    private fun setupRecyclerView() {
        adapter = UserListAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        adapter.updateList(emptyList())
    }

    private fun setupObservers() {
        viewModel.usersList.observe(this) { users ->
            if (users.isNullOrEmpty()) {
                showError()
            } else {
                binding.userListProgressBar.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                adapter.updateList(users)
            }
        }
    }

    private fun showError() {
        val message = getString(R.string.error)

        binding.userListProgressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE

        Toast.makeText(this@UserListActivity, message, Toast.LENGTH_SHORT).show()
    }
}
