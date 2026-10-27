package com.exploreolimpia.app

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView

class MainActivity : AppCompatActivity() {

    private lateinit var scrollView: NestedScrollView
    private lateinit var etBusca: EditText
    private lateinit var btnClearSearch: ImageView
    private lateinit var containerResultadosBusca: LinearLayout

    // Nav Bar Views
    private lateinit var iconNavInicio: ImageView
    private lateinit var labelNavInicio: TextView
    private lateinit var iconNavExplorar: ImageView
    private lateinit var labelNavExplorar: TextView
    private lateinit var iconNavRoteiro: ImageView
    private lateinit var labelNavRoteiro: TextView
    private lateinit var iconNavFavoritos: ImageView
    private lateinit var labelNavFavoritos: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupCategoryClicks()
        setupHighlightsClicks()
        setupAttractionClicks()
        setupHeroAndItineraryClicks()
        setupSearch()
        setupMenuAndFilter()
        setupBottomNav()
    }

    private fun initViews() {
        scrollView = findViewById(R.id.nestedScrollView)
        etBusca = findViewById(R.id.etBusca)
        btnClearSearch = findViewById(R.id.btnClearSearch)
        containerResultadosBusca = findViewById(R.id.containerResultadosBusca)

        iconNavInicio = findViewById(R.id.iconNavInicio)
        labelNavInicio = findViewById(R.id.labelNavInicio)
        iconNavExplorar = findViewById(R.id.iconNavExplorar)
        labelNavExplorar = findViewById(R.id.labelNavExplorar)
        iconNavRoteiro = findViewById(R.id.iconNavRoteiro)
        labelNavRoteiro = findViewById(R.id.labelNavRoteiro)
        iconNavFavoritos = findViewById(R.id.iconNavFavoritos)
        labelNavFavoritos = findViewById(R.id.labelNavFavoritos)
    }

    private fun showDetail(itemId: String) {
        val sheet = DetailBottomSheet.newInstance(itemId)
        sheet.show(supportFragmentManager, "detail_sheet_$itemId")
    }

    private fun setupCategoryClicks() {
        findViewById<View>(R.id.cardGastronomia).setOnClickListener {
            showDetail("cat_gastronomia")
        }
        findViewById<View>(R.id.cardParques).setOnClickListener {
            showDetail("cat_parques")
        }
        findViewById<View>(R.id.cardFeiras).setOnClickListener {
            showDetail("cat_feiras")
        }
        findViewById<View>(R.id.cardHoteis).setOnClickListener {
            showDetail("cat_hoteis")
        }
        findViewById<View>(R.id.cardEventos).setOnClickListener {
            showDetail("cat_eventos")
        }
        findViewById<View>(R.id.cardServicos).setOnClickListener {
            showDetail("cat_servicos")
        }
        findViewById<View>(R.id.cardPontosTuristicos).setOnClickListener {
            showDetail("cat_pontos")
        }
        findViewById<View>(R.id.cardLembrancas).setOnClickListener {
            showDetail("cat_museus")
        }
    }

    private fun setupHighlightsClicks() {
        findViewById<View>(R.id.destaqueGastronomia).setOnClickListener {
            showDetail("cat_gastronomia")
        }
        findViewById<View>(R.id.destaquePontos).setOnClickListener {
            showDetail("cat_pontos")
        }
        findViewById<View>(R.id.destaqueMuseus).setOnClickListener {
            showDetail("cat_museus")
        }
        findViewById<View>(R.id.destaqueServicos).setOnClickListener {
            showDetail("cat_servicos")
        }
    }

    private fun setupAttractionClicks() {
        findViewById<View>(R.id.cardThermas).setOnClickListener {
            showDetail("atracao_thermas")
        }
        findViewById<View>(R.id.cardHotBeach).setOnClickListener {
            showDetail("atracao_hotbeach")
        }
        findViewById<View>(R.id.cardValeDino).setOnClickListener {
            showDetail("atracao_valedino")
        }
        findViewById<View>(R.id.cardMuseuCera).setOnClickListener {
            showDetail("atracao_museucera")
        }
        findViewById<View>(R.id.cardRecintoFolclore).setOnClickListener {
            showDetail("atracao_recinto")
        }
    }

    private fun setupHeroAndItineraryClicks() {
        // Hero card (Vontade de viajar / Comece pelos parques)
        findViewById<View>(R.id.cardHero).setOnClickListener {
            showDetail("atracao_thermas")
        }
        findViewById<View>(R.id.btnHeroLink).setOnClickListener {
            showDetail("cat_parques")
        }

        // Convite para planejar roteiro
        findViewById<View>(R.id.cardPlanejarRoteiro).setOnClickListener {
            openRoteiroSheet()
        }
        findViewById<View>(R.id.btnComecarRoteiro).setOnClickListener {
            openRoteiroSheet()
        }
    }

    private fun openRoteiroSheet() {
        val sheet = RoteiroBottomSheet()
        sheet.show(supportFragmentManager, "roteiro_sheet")
    }

    private fun openFavoritesSheet() {
        val sheet = FavoritesBottomSheet()
        sheet.show(supportFragmentManager, "favorites_sheet")
    }

    private fun setupSearch() {
        etBusca.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s?.toString()?.trim() ?: ""
                if (query.isNotEmpty()) {
                    btnClearSearch.visibility = View.VISIBLE
                    performSearch(query)
                } else {
                    btnClearSearch.visibility = View.GONE
                    containerResultadosBusca.visibility = View.GONE
                    containerResultadosBusca.removeAllViews()
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        btnClearSearch.setOnClickListener {
            etBusca.text.clear()
        }
    }

    private fun performSearch(query: String) {
        val results = OlimpiaRepository.search(query)
        containerResultadosBusca.removeAllViews()

        if (results.isEmpty()) {
            containerResultadosBusca.visibility = View.VISIBLE
            val tvEmpty = TextView(this).apply {
                text = "Nenhum resultado encontrado para \"$query\"."
                setTextColor(resources.getColor(R.color.brand_text_muted, theme))
                textSize = 13f
                setPadding(16, 16, 16, 16)
            }
            containerResultadosBusca.addView(tvEmpty)
        } else {
            containerResultadosBusca.visibility = View.VISIBLE
            for (item in results) {
                val itemView = layoutInflater.inflate(R.layout.item_search_result, containerResultadosBusca, false)
                val ivIcon: ImageView = itemView.findViewById(R.id.ivResultIcon)
                val tvTitle: TextView = itemView.findViewById(R.id.tvResultTitle)
                val tvCategory: TextView = itemView.findViewById(R.id.tvResultCategory)

                ivIcon.setImageResource(item.iconRes)
                tvTitle.text = item.title
                tvCategory.text = item.category

                itemView.setOnClickListener {
                    showDetail(item.id)
                }
                containerResultadosBusca.addView(itemView)
            }
        }
    }

    private fun setupMenuAndFilter() {
        // Botão de menu no cabeçalho
        findViewById<View>(R.id.btnMenu).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Explore Olímpia - SP")
                .setMessage("🌟 Aplicativo oficial do seu guia turístico para Olímpia, a Capital Nacional do Folclore e Estância Turística de águas quentes.\n\nDesenvolvido com carinho para inspirar seu roteiro e tornar sua viagem inesquecível!")
                .setPositiveButton("Fechar", null)
                .setNeutralButton("Sobre Olímpia") { _, _ ->
                    showDetail("cat_pontos")
                }
                .show()
        }

        // Botão de filtros rápidos
        findViewById<View>(R.id.btnFiltro).setOnClickListener {
            val categories = arrayOf(
                "🌊 Parques Aquáticos",
                "🍽️ Gastronomia Caipira & Bares",
                "🦕 Atrações Temáticas",
                "🎭 Cultura e Folclore",
                "🏨 Hotéis e Resorts",
                "🛍️ Feiras & Compras",
                "🏥 Serviços Essenciais"
            )
            AlertDialog.Builder(this)
                .setTitle("Filtrar por Categoria")
                .setItems(categories) { _, which ->
                    when (which) {
                        0 -> showDetail("cat_parques")
                        1 -> showDetail("cat_gastronomia")
                        2 -> showDetail("atracao_valedino")
                        3 -> showDetail("atracao_recinto")
                        4 -> showDetail("cat_hoteis")
                        5 -> showDetail("cat_feiras")
                        6 -> showDetail("cat_servicos")
                    }
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }
    }

    private fun setupBottomNav() {
        findViewById<View>(R.id.navInicio).setOnClickListener {
            selectNavTab(0)
            scrollView.smoothScrollTo(0, 0)
        }

        findViewById<View>(R.id.navExplorar).setOnClickListener {
            selectNavTab(1)
            // Rola até a seção de categorias
            val targetY = findViewById<View>(R.id.cardGastronomia).top - 120
            scrollView.smoothScrollTo(0, if (targetY > 0) targetY else 350)
        }

        findViewById<View>(R.id.navRoteiro).setOnClickListener {
            selectNavTab(2)
            openRoteiroSheet()
        }

        findViewById<View>(R.id.navFavoritos).setOnClickListener {
            selectNavTab(3)
            openFavoritesSheet()
        }
    }

    private fun selectNavTab(index: Int) {
        val colorActive = resources.getColor(R.color.brand_primary, theme)
        val colorInactive = resources.getColor(R.color.brand_text_muted, theme)

        // Reseta todos
        iconNavInicio.setColorFilter(colorInactive)
        labelNavInicio.setTextColor(colorInactive)
        iconNavExplorar.setColorFilter(colorInactive)
        labelNavExplorar.setTextColor(colorInactive)
        iconNavRoteiro.setColorFilter(colorInactive)
        labelNavRoteiro.setTextColor(colorInactive)
        iconNavFavoritos.setColorFilter(colorInactive)
        labelNavFavoritos.setTextColor(colorInactive)

        // Ativa o selecionado
        when (index) {
            0 -> {
                iconNavInicio.setColorFilter(colorActive)
                labelNavInicio.setTextColor(colorActive)
            }
            1 -> {
                iconNavExplorar.setColorFilter(colorActive)
                labelNavExplorar.setTextColor(colorActive)
            }
            2 -> {
                iconNavRoteiro.setColorFilter(colorActive)
                labelNavRoteiro.setTextColor(colorActive)
            }
            3 -> {
                iconNavFavoritos.setColorFilter(colorActive)
                labelNavFavoritos.setTextColor(colorActive)
            }
        }
    }
}
