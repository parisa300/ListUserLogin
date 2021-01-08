package com.base.sampleapp_part.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.sampleapp_part.R
import com.base.sampleapp_part.data.entities.user
import com.base.sampleapp_part.databinding.FragmentUserBinding

import com.base.sampleapp_part.ui.adapters.UsersAdapter
import com.base.sampleapp_part.ui.viewmodel.UsersViewModel
import com.base.sampleapp_part.utils.Resource
import com.base.sampleapp_part.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserFragment : Fragment(),UsersAdapter.UserItemListener   {

    private var binding: FragmentUserBinding by autoCleared()
    private val viewModel: UsersViewModel by viewModels()
    private lateinit var adapter: UsersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = UsersAdapter(this)
        binding.usersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.usersRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.users.observe(viewLifecycleOwner, Observer {

                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                       if (it.data != null) {
                           Log.d("bbbb",it.data.size.toString())
                           val l: ArrayList<user> = ArrayList()
                           l.addAll(it.data)
                           adapter.setItems(l)
                       }

                    }
                    Resource.Status.ERROR ->
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                    Resource.Status.LOADING ->
                        binding.progressBar.visibility = View.VISIBLE
                }

        })
    }



    override fun onClickedCharacter(userId: Int) {
        findNavController().navigate(
            R.id.action_userFragment_to_detailFragment,
            bundleOf("id" to userId)
        )
    }
}