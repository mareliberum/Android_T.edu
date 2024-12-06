package com.example.myapplication.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.data.JOKE_ANSWER
import com.example.myapplication.data.JOKE_CATEGORY
import com.example.myapplication.data.JOKE_QUESTION
import com.example.myapplication.databinding.FragmentJokeDetailsBinding

class JokeDetailsFragment : Fragment() {

    private var _binding: FragmentJokeDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получение данных из аргументов
        val setup = arguments?.getString(JOKE_QUESTION)
        val delivery = arguments?.getString(JOKE_ANSWER)
        val category = arguments?.getString(JOKE_CATEGORY)

        // Установка данных в представление
        binding.question.text = setup
        binding.answer.text = delivery
        binding.category.text = category
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(setup: String, delivery: String, category: String): JokeDetailsFragment {
            val fragment = JokeDetailsFragment()
            val args = Bundle().apply {
                putString(JOKE_QUESTION, setup)
                putString(JOKE_ANSWER, delivery)
                putString(JOKE_CATEGORY, category)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
