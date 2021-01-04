package com.example.dagboek.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dagboek.MyApplication
import com.example.dagboek.R
import com.example.dagboek.databinding.FragmentListBinding
import com.example.dagboek.ui.MainActivity


/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {


    private val itemListVM: ItemListVM by viewModels {
        val application = requireActivity().application as MyApplication
        val itemRepo = application.itemRepo
        ItemListVMFactory(itemRepo)
    }

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        val mainActivity = activity as MainActivity

        // Create adapter for recyclerView so it can display the data correctly
        val adapter = ItemRecyclerViewAdapter(mainActivity, itemListVM.allItems)

        binding.itemRecycler.setHasFixedSize(true)
        binding.itemRecycler.layoutManager = LinearLayoutManager(activity)
        binding.itemRecycler.adapter = adapter
        binding.addButton.setOnClickListener { mainActivity.itemView() }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

}