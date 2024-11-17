import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Joke
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

        recyclerView = binding.recyclerView
        jokeAdapter = Adapter()

        recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(),1)
            adapter = jokeAdapter
        }

        // Инициализируем список шуток
        initialJokeList()


        val newJokes = listOf(
            Joke(8,"Math", "Why was the math book sad?", "Because it had too many problems."),
            Joke(9,"Science", "Why can't you trust an atom?", "Because they make up everything."),
            Joke(10,"Food", "Why don't eggs tell jokes?", "Because they might crack up.")
        )


        binding.refresh.setOnClickListener{

            Adapter.setItems(jokeAdapter, newJokes)
        }


    }

    //начальный список шуток
    private fun initialJokeList(){
        val initialJokes = listOf(
            Joke(
                1,
                "Christmas",
                "What does Santa suffer from if he gets stuck in a chimney?",
                "Claustrophobia!"
            ),
            Joke(2, "Math", "Why was the math book sad?", "Because it had too many problems."),
            Joke(3, "Animals", "Why don't some fish play piano?", "Because they can't tuna fish."),
            Joke(4, "Tech", "Why did the computer go to the doctor?", "Because it had a virus."),
            Joke(
                5,
                "School",
                "Why was the student's report card wet?",
                "Because it was below sea level."
            ),
            Joke(6, "Science", "Why can't you trust an atom?", "Because they make up everything."),
            Joke(7, "Food", "Why don't eggs tell jokes?", "Because they might crack up.")
        )
        Adapter.setItems(jokeAdapter, initialJokes)

    }
}
