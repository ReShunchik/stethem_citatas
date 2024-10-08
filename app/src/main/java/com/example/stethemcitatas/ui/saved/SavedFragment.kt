package com.example.stethemcitatas.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stethemcitatas.adapters.SavedQuoteAdapter
import com.example.stethemcitatas.databinding.FragmentSavedBinding
import com.example.stethemcitatas.db.QuoteDataBase
import com.hfad.tasks.SavedViewModelFactory

class SavedFragment : Fragment() {

    private var _binding: FragmentSavedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: SavedViewModel
    private lateinit var adapter: SavedQuoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val dao = QuoteDataBase.getInstance(application).QuoteDao()
        val viewModelFactory = SavedViewModelFactory(dao)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SavedViewModel::class.java)

        adapter = SavedQuoteAdapter(viewModel.savedQuotes, dao)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.quotesSavedList.layoutManager = LinearLayoutManager(requireContext())
        binding.quotesSavedList.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}