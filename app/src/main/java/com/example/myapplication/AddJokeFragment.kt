package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.data.JokeViewModel
import com.example.myapplication.data.db.Joke
import com.example.myapplication.databinding.FragmentAddJokeBinding

class AddJokeFragment : Fragment() {

    private lateinit var binding: FragmentAddJokeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAddJokeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val jokeViewModel: JokeViewModel by viewModels()

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
                    timeStamp = System.currentTimeMillis()
                )
                jokeViewModel.addJoke(newJoke)
                parentFragmentManager.popBackStack()
            }

        }


    }
}