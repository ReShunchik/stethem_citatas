package com.example.stethemcitatas.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stethemcitatas.adapters.QuoteAdapter
import com.example.stethemcitatas.databinding.FragmentHomeBinding
import com.example.stethemcitatas.db.QuoteDataBase
import com.hfad.tasks.HomeViewModelFactory



class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: QuoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val dao = QuoteDataBase.getInstance(application).QuoteDao()
        val viewModelFactory = HomeViewModelFactory(dao)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        adapter = QuoteAdapter(viewModel.fiveQuotes, dao)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.quotesList.layoutManager = LinearLayoutManager(requireContext())
        binding.quotesList.adapter = adapter

        binding.refreshButton.setOnClickListener{
            viewModel.refreshQuotes()
            adapter.refreshList(viewModel.fiveQuotes)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}