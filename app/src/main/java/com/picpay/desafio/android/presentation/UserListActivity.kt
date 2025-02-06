package com.picpay.desafio.android.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding

class UserListActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var adapter: UserListAdapter
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: UserListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(UserListViewModel::class.java)
        setContentView(binding.root)

        setupRecyclerView()
        setupObservers()
        viewModel.getUsers()
    }

    private fun setupRecyclerView() {
//        adapter = UsersListAdapter()
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

//    override fun onResume() {
//        super.onResume()
//
//        recyclerView = findViewById(R.id.recyclerView)
//        progressBar = findViewById(R.id.user_list_progress_bar)
//
//        adapter = UserListAdapter()
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        progressBar.visibility = View.VISIBLE
//        service.getUsers()
//            .enqueue(object : Callback<List<User>> {
//                override fun onFailure(call: Call<List<User>>, t: Throwable) {
//                    val message = getString(R.string.error)
//
//                    progressBar.visibility = View.GONE
//                    recyclerView.visibility = View.GONE
//
//                    Toast.makeText(this@UserListActivity, message, Toast.LENGTH_SHORT)
//                        .show()
//                }
//
//                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
//                    progressBar.visibility = View.GONE
//
//                    adapter.users = response.body()!!
//                }
//            })
//    }
}
