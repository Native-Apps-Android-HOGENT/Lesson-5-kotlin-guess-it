/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.title

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.database.WordDatabase
import com.example.android.guesstheword.databinding.TitleFragmentBinding
import com.example.android.guesstheword.domain.WordRepository
import com.example.android.guesstheword.network.WordApi

/**
 * Fragment for the starting or title screen of the app
 */
class TitleFragment : Fragment() {

    private lateinit var binding: TitleFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.title_fragment, container, false)

        val wordApiService = WordApi.retrofitService
        val wordDao = WordDatabase.getInstance(requireContext()).wordDao
        val connectivityManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val viewModelFactory = TitleViewModelFactory(WordRepository(wordDao, wordApiService, connectivityManager))
        binding.titleViewModel = ViewModelProviders.of(this, viewModelFactory).get(TitleViewModel::class.java)
        binding.lifecycleOwner = this

        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.playGameButton.setOnClickListener {
            findNavController().navigate(TitleFragmentDirections.actionTitleToGame())
        }

        binding.btnWords.setOnClickListener {
            findNavController().navigate(TitleFragmentDirections.actionTitleToWordListFragment())
        }
    }
}
