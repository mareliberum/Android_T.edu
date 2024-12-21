package com.example.myapplication.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.MyApp
import com.example.myapplication.data.db.Joke
import com.example.myapplication.data.db.JokeDao
import com.example.myapplication.databinding.FragmentJokeDetailsBinding
import com.example.myapplication.domain.JokeDbRepository
import javax.inject.Inject

class JokeDetailsFragment(
    val joke: Joke
) : Fragment() {

    private var _binding: FragmentJokeDetailsBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var jokeDbRepository: JokeDbRepository

    @Inject
    lateinit var jokeViewModelFactory: JokeViewModelFactory

    private val jokeViewModel: JokeViewModel by viewModels {JokeViewModelFactory(jokeDbRepository)}

    override fun onAttach(context: Context) {
        /**
        Берем activity, к которой прикреплен фрагмент, открываем application этой активити
        и приводим тип к MyApp, открываем из нее appComponent dagger и вызываем у него inject
         **/

        (requireActivity().application as MyApp).appComponent.inject(this)
        super.onAttach(context)
    }

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
            Log.d("test", "deletion btn clicked")
            if (joke.isFromNet){
                jokeViewModel.deleteJokeFromApiDatabase(joke.id)
                Log.d("test", "try to delete from api")
            }
            else{
                jokeViewModel.deleteJokeFromLocalDatabase(joke.id)
            }
            parentFragmentManager.popBackStack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
