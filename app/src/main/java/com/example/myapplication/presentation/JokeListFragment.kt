package com.example.myapplication.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MyApp
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentJokeListBinding
import com.example.myapplication.domain.JokeDbRepository
import com.example.myapplication.presentation.recycler.adapter.Adapter
import javax.inject.Inject

class JokeListFragment : Fragment() {

    @Inject
    lateinit var jokeDbRepository: JokeDbRepository

    @Inject
    lateinit var jokeViewModelFactory: JokeViewModelFactory

    val jokeViewModel: JokeViewModel by viewModels {JokeViewModelFactory(jokeDbRepository)}

    private lateinit var jokeAdapter: Adapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentJokeListBinding



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
        // Подключаем макет фрагмента
        binding = FragmentJokeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        jokeViewModel.fillStaticDb()

        jokeViewModel.jokeList.observe(viewLifecycleOwner) { jokeList ->
            Adapter.setItems(jokeAdapter, jokeList)
        }

        jokeViewModel.fetchJokes()

        jokeViewModel.isError.observe(viewLifecycleOwner) { isError ->
            if (isError) {
                errorHandler()
            }
        }

        jokeViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) binding.progressBar.visibility =
                View.VISIBLE else binding.progressBar.visibility = View.GONE
        }

        recyclerView = binding.recyclerView
        jokeAdapter = Adapter()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = jokeAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val totalItemCount = layoutManager.itemCount
                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                    // Проверяем, достигнут ли конец списка
                    if (lastVisibleItemPosition == totalItemCount - 1 && jokeViewModel.isLoading.value == false) {
                        jokeViewModel.loadFromApi()
                        jokeViewModel.fetchJokes()

                    }
                }
            })

        }

        binding.btnAddJoke.setOnClickListener {
            val fragment = AddJokeFragment()

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
        }
        binding.btnClearDb.setOnClickListener {
            jokeViewModel.clearDB()
        }
    }


    private fun errorHandler() {
        Toast.makeText(activity, "No internet connection!", Toast.LENGTH_LONG).show()

    }


}
