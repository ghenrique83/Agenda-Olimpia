package com.exploreolimpia.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FavoritesBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_favoritos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnClose: FrameLayout = view.findViewById(R.id.btnCloseFavoritos)
        val containerItems: LinearLayout = view.findViewById(R.id.containerFavoritosItems)
        val layoutEmpty: View = view.findViewById(R.id.layoutEmptyFavoritos)

        btnClose.setOnClickListener {
            dismiss()
        }

        val favorites = OlimpiaRepository.getFavoriteItems(requireContext())
        if (favorites.isEmpty()) {
            layoutEmpty.visibility = View.VISIBLE
        } else {
            layoutEmpty.visibility = View.GONE
            for (fav in favorites) {
                val itemView = layoutInflater.inflate(R.layout.item_search_result, containerItems, false)
                val ivIcon: ImageView = itemView.findViewById(R.id.ivResultIcon)
                val tvTitle: TextView = itemView.findViewById(R.id.tvResultTitle)
                val tvCategory: TextView = itemView.findViewById(R.id.tvResultCategory)

                ivIcon.setImageResource(fav.iconRes)
                tvTitle.text = fav.title
                tvCategory.text = fav.category

                itemView.setOnClickListener {
                    dismiss()
                    val detail = DetailBottomSheet.newInstance(fav.id)
                    detail.show(parentFragmentManager, "detail_sheet")
                }

                containerItems.addView(itemView)
            }
        }
    }
}
