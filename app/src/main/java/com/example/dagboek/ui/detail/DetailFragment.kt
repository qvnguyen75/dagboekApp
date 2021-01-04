package com.example.dagboek.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.dagboek.MyApplication
import com.example.dagboek.R
import com.example.dagboek.databinding.FragmentDetailBinding
import com.example.dagboek.domain.Item


const val ARG_ITEM_ID = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {


    private val itemDetailVM: ItemDetailVM by viewModels {
        val application = requireActivity().application as MyApplication
        val itemRepo = application.itemRepo
        ItemDetailVMFactory(itemRepo, itemId!!)
    }

//    private val repository by lazy { ItemRepository(requireContext()) }

    private var itemId: Int? = null

    private lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // shared prefs
//        itemId = requireActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
//                .getInt(ARG_ITEM_ID,-1)


        arguments?.let {
            itemId = it.getInt(ARG_ITEM_ID)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        val dataObserver = Observer<Item> {
            binding.item = itemDetailVM.item.value
        }

        itemDetailVM.item.observe(requireActivity(), dataObserver)
        binding.item = itemDetailVM.item.value

//        val id: Int = itemId ?: throw IllegalArgumentException()

        return binding.root
    }

    companion object {
//        const val ARG_ITEM_ID = "param1"
//        const val SHARED_PREFS = "zzzzzzzzz"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *

         * @return A new instance of fragment DetailFragment.
         */

        @JvmStatic
        fun newInstance(itemId: Int) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ITEM_ID, itemId)
                }
            }
    }
}