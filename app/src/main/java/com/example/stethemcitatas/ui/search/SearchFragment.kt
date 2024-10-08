package com.example.stethemcitatas.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stethemcitatas.adapters.QuoteAdapter
import com.example.stethemcitatas.databinding.FragmentSearchBinding
import com.example.stethemcitatas.db.QuoteDataBase
import java.lang.StringBuilder

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: QuoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val dao = QuoteDataBase.getInstance(application).QuoteDao()
        val viewModelFactory = SearchViewModelFactory(dao)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)

        adapter = QuoteAdapter(viewModel.searchQuotes, dao)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchQuotes.layoutManager = LinearLayoutManager(requireContext())
        binding.searchQuotes.adapter = adapter

        binding.buttonSearch.setOnClickListener{
            val str = binding.searchText.text.toString()
            val search = StringBuilder("%")
            search.append(str).append("%")
            viewModel.makeSearch(search)
            adapter.refreshList(viewModel.searchQuotes)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}