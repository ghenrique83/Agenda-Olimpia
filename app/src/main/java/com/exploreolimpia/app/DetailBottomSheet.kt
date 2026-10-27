package com.exploreolimpia.app

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetailBottomSheet : BottomSheetDialogFragment() {

    private var itemId: String = ""

    companion object {
        private const val ARG_ITEM_ID = "item_id"

        fun newInstance(itemId: String): DetailBottomSheet {
            val fragment = DetailBottomSheet()
            val args = Bundle()
            args.putString(ARG_ITEM_ID, itemId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemId = arguments?.getString(ARG_ITEM_ID) ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item = OlimpiaRepository.findById(itemId) ?: run {
            dismiss()
            return
        }

        val tvCategory: TextView = view.findViewById(R.id.tvSheetCategory)
        val tvTitle: TextView = view.findViewById(R.id.tvSheetTitle)
        val tvSubtitle: TextView = view.findViewById(R.id.tvSheetSubtitle)
        val tvDescription: TextView = view.findViewById(R.id.tvSheetDescription)
        val tvHighlights: TextView = view.findViewById(R.id.tvSheetHighlights)
        val tvTips: TextView = view.findViewById(R.id.tvSheetTips)

        val btnClose: FrameLayout = view.findViewById(R.id.btnSheetClose)
        val btnFavorite: FrameLayout = view.findViewById(R.id.btnSheetFavorite)
        val ivFavorite: ImageView = view.findViewById(R.id.ivSheetFavorite)
        val btnAddToItinerary: View = view.findViewById(R.id.btnAddToItinerary)
        val btnShare: View = view.findViewById(R.id.btnShare)

        // Preenche dados do item
        tvCategory.text = item.category
        tvTitle.text = item.title
        tvSubtitle.text = item.subtitle
        tvDescription.text = item.description
        tvHighlights.text = item.highlights
        tvTips.text = item.tips

        // Estado inicial de favoritos
        fun updateFavoriteIcon(isFav: Boolean) {
            if (isFav) {
                ivFavorite.setImageResource(R.drawable.ic_heart_filled)
                ivFavorite.setColorFilter(android.graphics.Color.parseColor("#E53935"))
            } else {
                ivFavorite.setImageResource(R.drawable.ic_heart)
                ivFavorite.setColorFilter(android.graphics.Color.parseColor("#007F82"))
            }
        }

        updateFavoriteIcon(OlimpiaRepository.isFavorite(requireContext(), item.id))

        btnFavorite.setOnClickListener {
            val newState = OlimpiaRepository.toggleFavorite(requireContext(), item.id)
            updateFavoriteIcon(newState)
            val msg = if (newState) "${item.title} adicionado aos favoritos!" else "${item.title} removido dos favoritos."
            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
        }

        btnClose.setOnClickListener {
            dismiss()
        }

        btnAddToItinerary.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "${item.title} adicionado com sucesso ao seu roteiro em Olímpia!",
                Toast.LENGTH_SHORT
            ).show()
        }

        btnShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(
                    Intent.EXTRA_SUBJECT,
                    "Explore Olímpia: ${item.title}"
                )
                putExtra(
                    Intent.EXTRA_TEXT,
                    "🌟 Conheça ${item.title} em Olímpia-SP!\n\n${item.subtitle}\n\n${item.description}\n\n📍 Dica: ${item.tips}\n\nDescubra mais no app Explore Olímpia!"
                )
            }
            startActivity(Intent.createChooser(shareIntent, "Compartilhar via"))
        }
    }
}
