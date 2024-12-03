package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentAddJokeBinding
import java.util.UUID

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

        binding.btnAddJoke.setOnClickListener{
            val category = binding.etCategory.text
            val question = binding.etQuestion.text
            val answer = binding.etAnswer.text

            if(category != null && question != null && answer != null){
                val newJoke = Joke(
                    id = UUID.randomUUID().toString(),
                    category = category.toString(),
                    question = question.toString(),
                    answer = answer.toString(),
                    isFromNet = false
                )
                JokeRepository.addToStart(newJoke)
                parentFragmentManager.popBackStack()
            }

        }


    }
}