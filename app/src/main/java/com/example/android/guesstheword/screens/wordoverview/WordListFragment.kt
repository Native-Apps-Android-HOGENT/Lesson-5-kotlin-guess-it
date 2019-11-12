package com.example.android.guesstheword.screens.wordoverview

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe

import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.WordListFragmentBinding
import com.example.android.guesstheword.screens.wordoverview.adapters.WordAdapter
import com.example.android.guesstheword.utilities.Injector
import kotlinx.android.synthetic.main.word_list_fragment.*

class WordListFragment : Fragment() {

    companion object {
        fun newInstance() = WordListFragment()
    }

    private val viewModel: WordListViewModel by lazy {
        ViewModelProviders.of(this, Injector.provideWordListViewModelFactory(requireContext())).get(
                WordListViewModel::class.java
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = WordListFragmentBinding.inflate(inflater,container,false)
        val adapter = WordAdapter()
        binding.wordList.adapter = adapter
        subscribeUi(adapter)
        return binding.root
    }

    private fun subscribeUi(adapter: WordAdapter){
        viewModel.words.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }


}
