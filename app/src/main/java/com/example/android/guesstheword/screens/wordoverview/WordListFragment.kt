package com.example.android.guesstheword.screens.wordoverview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.example.android.guesstheword.databinding.WordListFragmentBinding
import com.example.android.guesstheword.screens.wordoverview.adapters.WordAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordListFragment : Fragment() {

    private val viewModel: WordListViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = WordListFragmentBinding.inflate(inflater, container, false)
        val adapter = WordAdapter()
        binding.wordList.adapter = adapter
        subscribeUi(adapter)
        return binding.root
    }

    private fun subscribeUi(adapter: WordAdapter) {
        viewModel.words.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }


}
