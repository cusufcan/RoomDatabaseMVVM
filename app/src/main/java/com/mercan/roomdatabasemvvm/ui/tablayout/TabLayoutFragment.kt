package com.mercan.roomdatabasemvvm.ui.tablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mercan.roomdatabasemvvm.databinding.FragmentTabLayoutBinding
import com.mercan.roomdatabasemvvm.ui.tablayout.adapter.TabLayoutAdapter

class TabLayoutFragment : Fragment() {
    private var _binding: FragmentTabLayoutBinding? = null
    private val binding get() = _binding!!

    private lateinit var tabLayoutAdapter: TabLayoutAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabLayoutBinding.inflate(inflater, container, false)

        setTabLayoutAdapter()
        setOnClicks()

        return binding.root
    }

    fun getFab(): FloatingActionButton {
        return binding.createTaskFloatingActionButton
    }

    private fun setOnClicks() {
        binding.createTaskFloatingActionButton.setOnClickListener {
            findNavController().navigate(TabLayoutFragmentDirections.actionTabLayoutFragmentToTaskCreateFragment())
        }
    }

    private fun setTabLayoutAdapter() {
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        tabLayoutAdapter = TabLayoutAdapter(childFragmentManager, requireActivity())
        binding.viewPager.adapter = tabLayoutAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}