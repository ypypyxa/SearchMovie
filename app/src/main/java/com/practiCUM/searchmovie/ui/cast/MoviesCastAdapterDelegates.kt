package com.practiCUM.searchmovie.ui.cast

import com.practiCUM.searchmovie.core.ui.RVItem
import com.practiCUM.searchmovie.databinding.ItemHeaderListBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun movieCastHeaderDelegate() = adapterDelegateViewBinding<MoviesCastRVItem.HeaderItem, RVItem, ItemHeaderListBinding>(
    { layoutInflater, root -> ItemHeaderListBinding.inflate(layoutInflater, root, false) }
) {
    bind {
        binding.headerTextView.text = item.headerText
    }
}