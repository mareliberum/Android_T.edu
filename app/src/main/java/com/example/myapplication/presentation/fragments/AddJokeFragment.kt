package com.example.myapplication.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.MyApp
import com.example.myapplication.data.db.Joke
import com.example.myapplication.databinding.FragmentAddJokeBinding
import com.example.myapplication.domain.JokeDbRepository
import com.example.myapplication.presentation.viewModels.JokeViewModel
import com.example.myapplication.presentation.viewModels.JokeViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddJokeFragment : Fragment() {

    private lateinit var binding: FragmentAddJokeBinding
    @Inject
    lateinit var jokeDbRepository : JokeDbRepository

    @Inject
    lateinit var jokeViewModelFactory: JokeViewModelFactory

    private val jokeViewModel : JokeViewModel by viewModels { JokeViewModelFactory(jokeDbRepository) }

    override fun onAttach(context: Context) {
        (requireActivity().application as MyApp).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAddJokeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddJoke.setOnClickListener{
            val category = binding.etCategory.text
            val question = binding.etQuestion.text
            val answer = binding.etAnswer.text

            if(category != null && question != null && answer != null){
                val newJoke = Joke(
                    id = 0,
                    category = category.toString(),
                    setup = question.toString(),
                    delivery = answer.toString(),
                    isFromNet = false,
                    timeStamp = System.currentTimeMillis(),
                    isFavourite = false
                )

                lifecycleScope.launch {
                    jokeViewModel.addJokeToLocalDatabase(newJoke)
                }

                parentFragmentManager.popBackStack()
            }

        }


    }
}