package com.example.myapplication.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.data.db.Joke
import com.example.myapplication.data.db.JokeDao
import com.example.myapplication.databinding.FragmentJokeDetailsBinding

class JokeDetailsFragment(
    val joke: Joke,
    val jokeDao: JokeDao,
    val staticJokeDao: JokeDao
) : Fragment() {

    private var _binding: FragmentJokeDetailsBinding? = null
    private val binding get() = _binding!!
    private val jokeViewModel: JokeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Установка данных в представление
        binding.question.text = joke.setup
        binding.answer.text = joke.delivery
        binding.category.text = joke.category

        binding.deleteJoke.setOnClickListener{
            if (joke.isFromNet){
                jokeViewModel.delete(jokeDao, joke.id)
            }
            else{
                jokeViewModel.delete(staticJokeDao, joke.id)
            }
            parentFragmentManager.popBackStack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
