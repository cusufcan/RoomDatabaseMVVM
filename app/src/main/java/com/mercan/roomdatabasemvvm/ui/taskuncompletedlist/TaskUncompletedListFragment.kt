package com.mercan.roomdatabasemvvm.ui.taskuncompletedlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mercan.roomdatabasemvvm.R
import com.mercan.roomdatabasemvvm.databinding.FragmentTaskUncompletedListBinding
import com.mercan.roomdatabasemvvm.ui.taskuncompletedlist.adapter.TaskUncompletedListAdapter
import com.mercan.roomdatabasemvvm.viewmodel.TaskViewModel

class TaskUncompletedListFragment : Fragment() {
    private var _binding: FragmentTaskUncompletedListBinding? = null
    private val binding get() = _binding!!

    private val taskViewModel: TaskViewModel by viewModels()
    private lateinit var taskUncompletedListAdapter: TaskUncompletedListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskUncompletedListBinding.inflate(inflater, container, false)

        binding.taskRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        taskViewModel.uncompletedTasks.observe(viewLifecycleOwner) { tasks ->
            taskUncompletedListAdapter = TaskUncompletedListAdapter(tasks) { task ->
                taskViewModel.update(task)
            }
            binding.taskRecyclerView.adapter = taskUncompletedListAdapter
        }

        binding.createTaskFloatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_taskListFragment_to_taskCreateFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}