package by.annadanilenko.listfromroom.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.annadanilenko.listfromroom.data.model.dbroom.ItemUser
import by.annadanilenko.listfromroom.databinding.FragmentListBinding
import kotlinx.coroutines.launch

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ListViewModel by viewModels()

    private var listAdapter: ListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.startLoadUsersInfo()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.viewState.collect { state ->
                render(state)
            }
        }
    }

    private fun render(state: ListViewState) {
        showProgressBar(state.showProgressBar)
        when (state.listStatus) {
            is ListStatusDoNothing -> {
            }
            is ListStatusSuccess -> {
                showUsers(state.listStatus.users)
            }
            is ListStatusError -> {
                Toast.makeText(
                    requireContext(),
                    state.listStatus.errorDescription,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun showUsers(list: List<ItemUser>) {
        binding.rvUsers.layoutManager = LinearLayoutManager(
            this@ListFragment.activity
        )
        listAdapter = ListAdapter(list)
        binding.rvUsers.adapter = listAdapter
        binding.rvUsers.adapter?.notifyDataSetChanged()
    }
    private fun showProgressBar(show: Boolean) {
        binding.progressBar.isVisible = show
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}