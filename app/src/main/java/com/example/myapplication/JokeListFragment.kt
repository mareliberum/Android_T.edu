import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.AddJokeFragment
import com.example.myapplication.JokeRepository
import com.example.myapplication.JokeViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentJokeListBinding
import com.example.myapplication.recycler.adapter.Adapter

class JokeListFragment : Fragment() {
    private lateinit var jokeAdapter: Adapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentJokeListBinding

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

        val jokeViewModel: JokeViewModel by viewModels()

        jokeViewModel.loaded.observe(viewLifecycleOwner) { loaded ->
            if (loaded == true) Adapter.setItems(jokeAdapter, JokeRepository.getJokes())
        }

        jokeViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) binding.progressBar.visibility =
                View.VISIBLE else binding.progressBar.visibility = View.GONE
        }

        jokeViewModel.fetchJokes()

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
                        jokeViewModel.fetchJokes()
                    }
                }
            })

        }


        // Инициализируем список шуток
        Adapter.setItems(jokeAdapter, JokeRepository.getJokes())

        binding.btnAddJoke.setOnClickListener {
            val fragment = AddJokeFragment()

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
        }

    }
}
