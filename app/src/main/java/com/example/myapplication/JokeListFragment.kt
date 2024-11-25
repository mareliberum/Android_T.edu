import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.AddJokeFragment
import com.example.myapplication.JokeRepository
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentJokeListBinding
import com.example.myapplication.recycler.adapter.Adapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        recyclerView = binding.recyclerView
        jokeAdapter = Adapter()

        recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(),1)
            adapter = jokeAdapter
        }

        // Инициализируем список шуток
        Adapter.setItems(jokeAdapter, JokeRepository.getJokes())

        val newJokes = JokeRepository.newList()


        binding.refresh.setOnClickListener{
            Adapter.setItems(jokeAdapter, newJokes)
        }
        binding.btnAddJoke.setOnClickListener{
            val fragment = AddJokeFragment()

           parentFragmentManager.beginTransaction()
               .replace(R.id.fragmentContainer, fragment)
               .addToBackStack(null)
               .commit()
        }



    }

    override fun onResume() {
        super.onResume()
        fetchJokes()
    }

    private fun fetchJokes(){
        binding.progressBar.visibility = View.VISIBLE
        binding.emptyTextView.visibility = View.GONE

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            val jokes = withContext(Dispatchers.IO) {
                JokeRepository.getJokes()
            }
            binding.progressBar.visibility = View.GONE
            if (jokes.isEmpty()) {
                binding.emptyTextView.visibility = View.VISIBLE
            } else {
                Adapter.setItems(jokeAdapter, jokes)
            }
        }

    }
}
