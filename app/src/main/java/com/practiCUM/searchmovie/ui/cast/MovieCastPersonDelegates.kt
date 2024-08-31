package com.practiCUM.searchmovie.ui.cast

import com.practiCUM.searchmovie.core.ui.RVItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.practiCUM.searchmovie.databinding.ItemCastListBinding
import androidx.core.view.isVisible
import com.bumptech.glide.Glide

fun movieCastPersonDelegate() = adapterDelegateViewBinding<MoviesCastRVItem.PersonItem, RVItem, ItemCastListBinding>(
    { layoutInflater, root -> ItemCastListBinding.inflate(layoutInflater, root, false) }
) {
    bind {
        if (item.data.image == null) {
            binding.actorImageView.isVisible = false
        } else {
            Glide.with(itemView)
                .load(item.data.image)
                .into(binding.actorImageView)
            binding.actorImageView.isVisible = true
        }

        binding.actorNameTextView.text = item.data.name
        binding.actorDescriptionTextView.text = item.data.description
    }
}