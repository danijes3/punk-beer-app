package com.dani.punkbeerapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dani.punkbeerapp.R
import com.dani.punkbeerapp.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso

class DetailsFragment() : Fragment() {
    private lateinit var fragmentDetailsBinding: FragmentDetailsBinding
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentDetailsBinding = FragmentDetailsBinding.bind(view)

        arguments?.let {
            val position = it.getInt("position")
            adapter = (activity as MainActivity).adapter
            val beer = adapter.differ.currentList[position]
            fragmentDetailsBinding.name.text = beer.name
            fragmentDetailsBinding.abv.text = "ABV " + beer.abv.toString()
            fragmentDetailsBinding.tagline.text = beer.tagline
            fragmentDetailsBinding.description.text = beer.description
            fragmentDetailsBinding.tagline.text = beer.tagline
            fragmentDetailsBinding.tips.text = beer.brewersTips

            val imageUrl = beer.imageUrl
            Picasso.with(fragmentDetailsBinding.detailsImageView.context).
            load(imageUrl).into(fragmentDetailsBinding.detailsImageView)
        }
    }
}