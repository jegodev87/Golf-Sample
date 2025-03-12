package com.sample.golf.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.golf.R
import com.sample.golf.data.source.remote.NetworkResult
import com.sample.golf.databinding.FragmentGolfCoursesBinding
import com.sample.golf.domain.model.GolfCourse
import com.sample.golf.ui.adapter.GolfCoursesAdapter
import com.sample.golf.utils.hide
import com.sample.golf.utils.log
import com.sample.golf.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [GolfCoursesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class GolfCoursesFragment : Fragment() {
    private var _binding: FragmentGolfCoursesBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: GolfViewModel by viewModels()
    private var searchJob: Job? = null
    private var goldCoursesAdapter : GolfCoursesAdapter ? = null
    private var mGolfCoursesList = mutableListOf<GolfCourse>()

    // Debouncing delay (in milliseconds)
    private val debounceDelay = 300L


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGolfCoursesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeAdapter()
        bindRecyclerview()
        initiateSearch()
        viewmodel.searchGolfCourses("")

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewmodel.golfCourses.collect {
                    when(it){
                        is NetworkResult.Error ->{
                           showLoading(false)
                        }
                        is NetworkResult.Exception -> {
                               showLoading(false)
                        }
                        NetworkResult.Loading -> {
                            showLoading(true)

                        }
                        is NetworkResult.Success -> {
                            mGolfCoursesList.clear()
                            if (it.data.isNotEmpty()){
                                mGolfCoursesList.addAll(it.data)
                                binding.tvStatus.text = ""
                            }else{
                                binding.tvStatus.text = getString(R.string.label_course_no_result)
                            }
                            goldCoursesAdapter?.submitList(mGolfCoursesList.toMutableList()){
                                showLoading(false)
                            }

                        }
                    }
                }
            }
        }
    }

    private fun initializeAdapter(){
        goldCoursesAdapter = GolfCoursesAdapter(){

        }
    }

    private fun bindRecyclerview(){
        val dividerItemDecoration = DividerItemDecoration(
            requireContext(),
            LinearLayoutManager.VERTICAL // Set the orientation of the divider
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = goldCoursesAdapter
            itemAnimator = null
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun initiateSearch(){
        binding.etSearchCourses.addTextChangedListener {
            searchJob?.cancel()
            searchJob = viewLifecycleOwner.lifecycleScope.launch {
                // Collect the flow when the fragment's lifecycle is at least STARTED
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    val query = it.toString().trim()
                    delay(debounceDelay) // Wait for 300ms after the last typing event
                    viewmodel.searchGolfCourses(query) // Trigger search in ViewModel

                }

            }
        }

        binding.textinputSearch.setEndIconOnClickListener {
            binding.etSearchCourses.text = null
            goldCoursesAdapter?.submitList(emptyList())
        }
    }

    private fun showLoading(status: Boolean){
        binding.shimmer.isVisible = status
        if (status) {
            binding.shimmer.startShimmer()
            binding.recyclerView.hide()
            binding.tvStatus.hide()
        }else {
            binding.recyclerView.show()
            binding.shimmer.stopShimmer()
            binding.tvStatus.isVisible = mGolfCoursesList.isEmpty()
        }
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GolfCoursesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GolfCoursesFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}