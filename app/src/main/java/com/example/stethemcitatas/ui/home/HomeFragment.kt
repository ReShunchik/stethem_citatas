package com.example.stethemcitatas.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.room.util.newStringBuilder
import com.example.stethemcitatas.databinding.FragmentHomeBinding
import com.example.stethemcitatas.db.Quote
import com.example.stethemcitatas.db.QuoteDataBase
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var db: QuoteDataBase
    private lateinit var textView: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = requireNotNull(this.activity).application
        db = QuoteDataBase.getInstance(application)
        viewLifecycleOwner.lifecycleScope.launch {
            val listItems = arrayListOf<Quote>()
            listItems.addAll(db.QuoteDao().getAll())
            val someQuote = StringBuilder()
            someQuote.append(listItems.get(65).quote).append("\n")
            someQuote.append(listItems.get(198).quote)

            binding.citataOfDay.text = someQuote
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}