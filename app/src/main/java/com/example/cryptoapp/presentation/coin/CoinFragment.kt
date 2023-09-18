package com.example.cryptoapp.presentation.coin

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ActivityMainBinding
import com.example.cryptoapp.databinding.FragmentCoinBinding
import com.example.cryptoapp.domain.model.Coin
import com.example.cryptoapp.presentation.coinlist.CoinAdapter
import com.example.cryptoapp.presentation.coinlist.CoinListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CoinFragment : Fragment(),SearchView.OnQueryTextListener {

    private lateinit var coinAdapter: CoinAdapter
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var binding: FragmentCoinBinding
    private var page: Int = 1
    private val tempCoinList = arrayListOf<Coin>()
    private val coinListViewModel: CoinListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Используйте ViewBinding для надувания макета и получения экземпляра биндинга
        binding = FragmentCoinBinding.inflate(inflater, container, false)
        val view = binding.root
        setUpTheRecyclerView()
        layoutManager = GridLayoutManager(requireContext(), 2)
        binding.tbSort.setOnClickListener {
            tempCoinList.sortWith { o1, o2 -> o1.name.compareTo(o2.name) }
            coinAdapter.setData(tempCoinList)
        }
        callAPI()

        binding.coinRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount - 1) {
                    page += 1
                    coinListViewModel.getAllCoins(page.toString())
                    callAPI()
                }
            }
        })

        return view
    }

    private fun callAPI() {
        coinListViewModel.getAllCoins(page.toString())
        CoroutineScope(Dispatchers.IO).launch {
            coinListViewModel._coinListValue.collectLatest { coinListValue ->
                if (coinListValue.isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    if (coinListValue.error.isNotBlank()) {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), coinListValue.error, Toast.LENGTH_LONG)
                            .show()
                    } else {
                        binding.progressBar.visibility = View.GONE
                        tempCoinList.addAll(coinListValue.coinList)
                        coinAdapter.setData(tempCoinList as ArrayList<Coin>)
                    }
                }
            }
        }
    }

    private fun setUpTheRecyclerView() {
        coinAdapter = CoinAdapter(requireContext(), ArrayList())
        binding.coinRecyclerView.adapter = coinAdapter
        binding.coinRecyclerView.layoutManager = layoutManager
        binding.coinRecyclerView.addItemDecoration(
            DividerItemDecoration(requireContext(),
                (binding.coinRecyclerView.layoutManager as GridLayoutManager).orientation)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val search = menu.findItem(R.id.menuSearch)
        val searchView = search?.actionView as androidx.appcompat.widget.SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText?.isEmpty()!!) {
            coinAdapter.setData(tempCoinList)
        }else {
            coinAdapter.filter.filter(newText)
        }
        return true
    }


}