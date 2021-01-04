package com.example.dagboek.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.dagboek.MyApplication
import com.example.dagboek.R
import com.example.dagboek.databinding.FragmentAddBinding
import com.example.dagboek.domain.Item
import java.text.SimpleDateFormat
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private val itemRepo by lazy { (requireActivity().application as MyApplication).itemRepo }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false)

        binding.submitButton.setOnClickListener {
            val formattedDate = SimpleDateFormat("yyyy-MM-dd").format(Date())
//            val formattedTime = SimpleDateFormat("HH:mm").format(Date())

            try {
                val newItem = Item(
                    title = binding.titleView.text.toString(),
                    body = binding.bodyView.text.toString(),
                    date = formattedDate
                )
                itemRepo.add(newItem)
                requireActivity().supportFragmentManager.popBackStack()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *

         * @return A new instance of fragment AddFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = AddFragment()
    }
}