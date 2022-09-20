package com.dani.punkbeerapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dani.punkbeerapp.R
import com.dani.punkbeerapp.data.util.Resource
import com.dani.punkbeerapp.databinding.ActivityMainBinding
import com.dani.punkbeerapp.databinding.ActivityMainBinding.inflate
import com.dani.punkbeerapp.presentation.viewmodel.BeerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    @Inject
    lateinit var adapter: RecyclerViewAdapter
    private val beerViewModel: BeerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        viewBeers()
        searchView()
    }

    private fun viewBeers() {
        var pageCont = 1
        beerViewModel.getBeers(pageCont, 80)
        beerViewModel.beerList.observe(this) { response ->
            when (response) {
                is Resource.Success -> {
                    hideLoading()
                    response.data?.let {
                        adapter.differ.submitList(it.toList())
                    }
                }
                is Resource.Error -> {
                    hideLoading()
                    response.message?.let {
                        Log.i("TAG" ,"Error: $it")
                    }
                }
                is Resource.Loading -> {
                    showLoading()
                }
            }
        }
    }

    private fun viewBeersByName() {
        beerViewModel.beerListByName.observe(this) { response ->
            when (response) {
                is Resource.Success -> {
                    hideLoading()
                    response.data?.let {
                        adapter.differ.submitList(it.toList())
                    }
                }
                is Resource.Error -> {
                    hideLoading()
                    response.message?.let {
                        Log.i("TAG" ,"Error: $it")
                    }
                }
                is Resource.Loading -> {
                    showLoading()
                }
            }
        }
    }

    private fun searchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
//                beerViewModel.getBeersByName(1, 20, p0.toString())
//                viewBeersByName()
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                MainScope().launch {
                    beerViewModel.getBeersByName(1, 20, p0.toString())
                    viewBeersByName()
                    delay(1000)
                }
                return false
            }
        })

        binding.searchView.setOnCloseListener {
            initRecyclerView()
            viewBeers()
            false
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.setOnItemClickListener(object : RecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val fragment = DetailsFragment()
                val bundle = Bundle()
                bundle.putInt("position", position)
                fragment.arguments = bundle
                showFragment(fragment)
            }

        })
        binding.recyclerView.adapter = adapter
    }

    private fun showLoading(){
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading(){
        binding.progressBar.visibility = View.GONE
    }

    private fun showFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}