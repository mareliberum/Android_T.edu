package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        val question = arguments?.getString(JOKE_QUESTION)
        val answer = arguments?.getString(JOKE_ANSWER)
        val category = arguments?.getString(JOKE_CATEGORY)

        // Установка данных в представление
        binding.question.text = question
        binding.answer.text = answer
        binding.category.text = category
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(question: String, answer: String, category: String): JokeDetailsFragment {
            val fragment = JokeDetailsFragment()
            val args = Bundle().apply {
                putString(JOKE_QUESTION, question)
                putString(JOKE_ANSWER, answer)
                putString(JOKE_CATEGORY, category)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
