package com.example.stethemcitatas.ui.home

import DataBaseHelper
import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.stethemcitatas.databinding.FragmentHomeBinding
import com.example.stethemcitatas.db.DbManager
import com.example.stethemcitatas.db.DbName

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var dbHelper: DataBaseHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dbHelper = DataBaseHelper(requireContext())
        dbHelper.openDatabase()
        val cursor: Cursor = dbHelper.readableDatabase.rawQuery("SELECT * FROM ${DbName.TABLE_NAME}", null)
        if (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndexOrThrow(DbName.COLUMN_NAME_CITATA))
            _binding!!.citataOfDay.text = name
        }
        cursor.close()
        dbHelper.close()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}