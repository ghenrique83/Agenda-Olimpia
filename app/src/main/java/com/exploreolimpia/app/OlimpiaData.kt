package com.exploreolimpia.app

import android.content.Context
import android.content.SharedPreferences

data class OlimpiaItem(
    val id: String,
    val title: String,
    val category: String,
    val subtitle: String,
    val description: String,
    val highlights: String,
    val tips: String,
    val iconRes: Int
)

object OlimpiaRepository {

    private const val PREFS_NAME = "explore_olimpia_prefs"
    private const val KEY_FAVORITES = "user_favorites"

    val items: List<OlimpiaItem> = listOf(
        // ATRAÇÃO 1: THERMAS DOS LARANJAIS
        OlimpiaItem(
            id = "atracao_thermas",
            title = "Thermas dos Laranjais",
            category = "PARQUES AQUÁTICOS",
            subtitle = "4º parque aquático mais visitado do mundo e orgulho de Olímpia",
            description = "O Thermas dos Laranjais é um dos maiores complexos de águas termais do planeta. Com águas naturalmente quentes (temperaturas entre 26°C e 38°C) que brotam do Aquífero Guarani a quase 1.000 metros de profundidade, o parque oferece mais de 55 atrações para todas as idades em uma área verde exuberante de mais de 300 mil metros quadrados.",
            highlights = "• Montanha-russa aquática pioneira na América Latina\n• Complexo 'Lendário' com toboáguas de queda livre de até 28 metros\n• Piscina de surfe Master 180° e pista de asa-delta\n• Praias artificiais com ondas e areia branca\n• Rio selvagem com corredeiras e rio lento relaxante\n• Piscinas de hidromassagem e ofurôs termais",
            tips = "• Horário: diariamente das 09h às 17h.\n• Compre ingressos com antecedência online para evitar filas na bilheteria.\n• Leve sapatilha aquática ou chinelos: o piso pode esquentar com o sol forte do interior paulista.\n• O parque funciona com cartão recarregável para consumo de refeições e armários.",
            iconRes = R.drawable.ic_waves
        ),

        // ATRAÇÃO 2: HOT BEACH
        OlimpiaItem(
            id = "atracao_hotbeach",
            title = "Hot Beach Olímpia",
            category = "PARQUES AQUÁTICOS",
            subtitle = "Praia termal paradisíaca com areia branca de verdade",
            description = "O Hot Beach Olímpia é o segundo parque aquático mais visitado da cidade e um dos mais modernos do Brasil. Seu grande diferencial é a atmosfera relaxante de resort praia: areia branca e macia, coqueiros, ondas suaves e água límpida e aquecida. É o destino preferido para famílias com crianças pequenas e quem busca sossego com alta gastronomia.",
            highlights = "• Praia artificial com ondas mornas e areia natural\n• 'Eba River': rio de correnteza suave para navegar com boias\n• Complexo de toboáguas radicais como 'Irajá' e 'Oca do Índio'\n• Área kids gigante com brinquedos interativos e salva-vidas constantes\n• Cabanas vips com atendimento de garçom exclusivo\n• Acesso integrado à Vila Guarani, polo gastronômico noturno",
            tips = "• Horário: de terça a domingo das 09h às 17h.\n• Dica de ouro: hóspedes dos resorts Hot Beach têm entrada gratuita inclusa na diária durante toda a estadia!\n• Excelente acessibilidade para carrinhos de bebê e cadeirantes.",
            iconRes = R.drawable.ic_waves
        ),

        // ATRAÇÃO 3: VALE DOS DINOSSAUROS
        OlimpiaItem(
            id = "atracao_valedino",
            title = "Vale dos Dinossauros",
            category = "ATRAÇÕES TEMÁTICAS",
            subtitle = "Viagem jurássica com réplicas animatrônicas gigantes",
            description = "O Vale dos Dinossauros de Olímpia transporta os visitantes para a era pré-histórica. O parque conta com dezenas de réplicas de dinossauros em tamanho real que respiram, movimentam a cabeça e a cauda e emitem rugidos autênticos, instalados em meio a uma mata nativa preservada.",
            highlights = "• Réplica do temível Tiranossauro Rex com mais de 15 metros de comprimento\n• Braquiossauros, velociraptores e pterodáctilos gigantes\n• Dino Kart: pista temática de kart para crianças e adultos\n• Cinema 7D com efeitos sensoriais de vento, chuva e movimento\n• Sítio paleontológico infantil para escavação de fósseis\n• Loja de lembranças jurássicas exclusivas",
            tips = "• Horário: aberto diariamente das 09h às 21h.\n• Passeio ideal para o final de tarde após sair do parque aquático.\n• Existem combos promocionais de ingressos combinados com o Museu de Cera e Bar de Gelo.",
            iconRes = R.drawable.ic_camera
        ),

        // ATRAÇÃO 4: DREAMLAND MUSEU DE CERA
        OlimpiaItem(
            id = "atracao_museucera",
            title = "Dreamland Museu de Cera",
            category = "CULTURA E ENTRETENIMENTO",
            subtitle = "Mais de 70 celebridades mundiais em cenários imersivos",
            description = "O Dreamland Museu de Cera é uma das atrações cobertas mais famosas de Olímpia. São dezenas de estátuas em tamanho real, esculpidas nos mesmos ateliês europeus que atendem os principais museus de cera do mundo. Você pode ficar cara a cara com ídolos do cinema, da música, do esporte e da história.",
            highlights = "• Super-heróis da Marvel e DC (Homem de Ferro, Capitão América, Batman)\n• Ícones do cinema como Star Wars, Harry Potter, Piratas do Caribe e Jack Sparrow\n• Lendas do esporte: Ayrton Senna, Pelé, Messi, Neymar e Cristiano Ronaldo\n• Astros da música: Michael Jackson, The Beatles, Amy Winehouse e Elvis Presley\n• Cenários altamente instagramáveis com iluminação artística",
            tips = "• Horário: aberto todos os dias das 09h às 22h.\n• Excelente opção para dias chuvosos ou noites em família.\n• Traga a câmera bem carregada para fotos inesquecíveis ao lado dos seus personagens favoritos.",
            iconRes = R.drawable.ic_landmark
        ),

        // ATRAÇÃO 5: RECINTO DO FOLCLORE
        OlimpiaItem(
            id = "atracao_recinto",
            title = "Recinto do Folclore Prof. José Sant'anna",
            category = "CULTURA E FOLCLORE",
            subtitle = "Capital Nacional do Folclore e sede do renomado FEFOL",
            description = "Olímpia é oficialmente reconhecida por lei federal como a 'Capital Nacional do Folclore'. O Recinto do Folclore Prof. José Sant’anna é um complexo grandioso de 110 mil m² que sedia anualmente o Festival Nacional do Folclore (FEFOL), reunindo mais de 60 grupos folclóricos de todos os estados do Brasil.",
            highlights = "• Palco oficial do tradicional Festival Nacional do Folclore (todo mês de agosto)\n• Arquibancadas cobertas e arenas de dança típica (Catira, Congadas, Maracatu, Bumba Meu Boi)\n• Pavilhões com feiras de artesanato típico caipira e de raízes brasileiras\n• Barracas com culinária autêntica regional de cada estado brasileiro\n• Trilha ecológica e monumento do folclore",
            tips = "• Entrada franca durante grande parte do ano para caminhadas e visitas históricas.\n• Se estiver na cidade na primeira quinzena de agosto, o festival é parada obrigatória gratuita!\n• Visite também o Museu de História e Folclore Maria Olímpia situado bem próximo.",
            iconRes = R.drawable.ic_ticket
        ),

        // CATEGORIA 1: GASTRONOMIA
        OlimpiaItem(
            id = "cat_gastronomia",
            title = "Gastronomia em Olímpia",
            category = "SABORES & TRADIÇÃO",
            subtitle = "Do fogão a lenha caipira às melhores choperias e pizzarias",
            description = "A culinária de Olímpia combina a rica tradição caipira do interior paulista com a gastronomia contemporânea de uma estância turística de classe mundial. Você encontra desde leitoa à pururuca, arroz carreteiro e galinhada caipira feita na lenha, até hamburguerias gourmet, restaurantes italianos e bares descolados.",
            highlights = "• Avenida Aurora Forti Neves: o grande corredor gastronômico iluminado da cidade\n• Restaurantes de comida típica no fogão a lenha (como Restaurante Dat Badan e O Botequim)\n• Vila Guarani: espaço charmoso com música ao vivo, chope artesanal e petiscos\n• Doces caseiros tradicionais: compotas de figo, doce de leite em tacho de cobre e goiabadas\n• Cafés coloniais matinais e sorveterias artesanais de frutas regionais",
            tips = "• No final de tarde, os bares e choperias da Av. Aurora Forti Neves são o ponto de encontro perfeito dos turistas.\n• Não deixe de experimentar a cachaça artesanal da região e os queijos caipiras produzidos nos arredores.",
            iconRes = R.drawable.ic_utensils
        ),

        // CATEGORIA 2: PARQUES
        OlimpiaItem(
            id = "cat_parques",
            title = "Parques e Águas Termais",
            category = "DIVERSÃO & DESCANSO",
            subtitle = "O epicentro das águas quentes do interior paulista",
            description = "Olímpia é carinhosamente apelidada de 'Orlando Brasileira' por concentrar alguns dos melhores parques do país. Suas águas termais brotam com temperaturas amenas e relaxantes, criando um clima tropical e ensolarado durante quase todos os meses do ano.",
            highlights = "• Thermas dos Laranjais: mais de 55 atrações aquáticas de pura adrenalina e relaxamento\n• Hot Beach Olímpia: parque com conceito praia, areia branquinha e rio lento\n• Bar de Gelo & Pit Stop da Neve: contraste surpreendente com temperaturas de até -10°C em pleno calor!\n• Orionverso: parque temático imersivo de realidade virtual e inteligência artificial",
            tips = "• Reserve pelo menos 2 dias inteiros só para os parques aquáticos para conseguir aproveitar sem pressa.\n• Mantenha-se hidratado e use protetor solar biodegradável.",
            iconRes = R.drawable.ic_waves
        ),

        // CATEGORIA 3: FEIRAS
        OlimpiaItem(
            id = "cat_feiras",
            title = "Feiras e Compras",
            category = "ENCONTROS E ACHADOS",
            subtitle = "Artesanato autêntico, lembrancinhas e moda praia",
            description = "Fazer compras em Olímpia é levar para casa um pedacinho da cultura caipira e folclórica brasileira. A cidade oferece feirinhas ao ar livre, centros comerciais e galerias especializadas em vestuário de praia e produtos artesanais.",
            highlights = "• Feirinha de Artesanato do Recinto do Folclore: esculturas em madeira, bordados e cerâmica\n• Shopping Villa Mall Olímpia: ambiente agradável com lojas de marcas, cinema e praça de alimentação\n• Lojas da região central: ampla variedade de roupas de banho, boias infláveis e souvenirs\n• Empórios caipiras: queijos, cachaças premiadas, pimentas especiais e farinhas artesanais",
            tips = "• As lembrancinhas mais procuradas são miniaturas do Boi-bumbá, panelas de pedra-sabão e compotas de doces artesanais.",
            iconRes = R.drawable.ic_store
        ),

        // CATEGORIA 4: HOTÉIS E POUSADAS
        OlimpiaItem(
            id = "cat_hoteis",
            title = "Hotéis e Pousadas",
            category = "SEU LUGAR PARA FICAR",
            subtitle = "A 2ª maior rede hoteleira de SP com mais de 35 mil leitos",
            description = "Olímpia possui uma infraestrutura hoteleira extraordinária. De mega resorts de categoria internacional com acesso privativo aos parques a pousadas rurais aconchegantes e acolhedoras, há acomodação perfeita para todos os estilos de viagem e bolsos.",
            highlights = "• Resorts integrados aos parques aquáticos (Thermas Park, Hot Beach Resort, Wyndham Olímpia)\n• Hotéis com complexos de piscinas aquecidas próprias e equipe de recreação infantil\n• Pousadas charmosas no centro histórico com excelente custo-benefício\n• Chalés e fazendas com clima bucólico de campo e café colonial completo",
            tips = "• Dica de economia: na baixa temporada (março a junho, agosto a novembro) há excelentes promoções e pacotes com ingressos inclusos.\n• Reserve com antecedência para feriados prolongados e para a semana do Folclore.",
            iconRes = R.drawable.ic_bed
        ),

        // CATEGORIA 5: EVENTOS
        OlimpiaItem(
            id = "cat_eventos",
            title = "Eventos e Celebrações",
            category = "VIVA NOVOS ENCONTROS",
            subtitle = "A vibração do folclore e o calendário cultural o ano todo",
            description = "Olímpia respira cultura e festividades. O calendário da cidade conta com espetáculos que celebram as raízes mais profundas da identidade nacional, além de eventos esportivos, náuticos e réveillons disputados.",
            highlights = "• FEFOL - Festival Nacional do Folclore: o maior evento folclórico do Brasil (agosto)\n• Festa de São João Batista: celebração do padroeiro municipal com quermesse tradicional\n• Aniversário de Olímpia (2 de março): desfiles cívicos e shows musicais nacionais\n• Festival Gastronômico de Inverno e eventos nos resorts termais",
            tips = "• Em agosto, a cidade se transforma: desfile pelas ruas principais com grupos de Maracatu, Catira e Reisado.",
            iconRes = R.drawable.ic_ticket
        ),

        // CATEGORIA 6: SERVIÇOS ESSENCIAIS
        OlimpiaItem(
            id = "cat_servicos",
            title = "Serviços Essenciais & Apoio",
            category = "APOIO PARA SUA VIAGEM",
            subtitle = "Tudo para uma estadia tranquila, segura e confortável",
            description = "Viajar com tranquilidade requer saber onde encontrar apoio quando necessário. Olímpia conta com uma rede eficiente de saúde pública e privada, postos de informações turísticas e transporte acessível.",
            highlights = "• Centro de Informações Turísticas (CIT): mapas, guias e dicas oficiais gratuitas\n• UPA 24 Horas de Olímpia e Santa Casa de Misericórdia para emergências médicas\n• Redes de farmácias com plantão 24h na região central e na Av. Aurora Forti Neves\n• Terminal Rodoviário Intermunicipal com conexões para São Paulo, Rio Preto e Barretos\n• Serviços de táxi, transporte por aplicativo e transfers para aeroportos vizinhos",
            tips = "• O aeroporto mais próximo com voos comerciais regulares é o de São José do Rio Preto (SJP), a apenas 45 minutos de carro de Olímpia.\n• Telefone útil do CIT Olímpia: (17) 3280-6294.",
            iconRes = R.drawable.ic_medical
        ),

        // CATEGORIA 7: PONTOS TURÍSTICOS
        OlimpiaItem(
            id = "cat_pontos",
            title = "Pontos Turísticos da Cidade",
            category = "OLHE A CIDADE DE PERTO",
            subtitle = "Encantos históricos, praças e recantos da estância turística",
            description = "Além dos famosos parques de águas termais, Olímpia guarda recantos históricos acolhedores, praças arborizadas com fontes luminosas e monumentos que contam a história da expansão cafeeira e da imigração italiana e sírio-libanesa.",
            highlights = "• Praça da Matriz de São João Batista: coração histórico com jardins e coreto\n• Igreja Matriz de São João Batista: belíssima arquitetura sacra e vitrais\n• Cristo Redentor de Olímpia com vista panorâmica da cidade\n• Bosque Municipal: área verde com lago e trilhas para caminhadas tranquilas\n• Polo turístico da Avenida Benjamim Constant e Monumento do Folclore",
            tips = "• Vale a pena um passeio a pé no início da noite pela Praça da Matriz para tomar um sorvete e sentir a atmosfera hospitaleira da cidade.",
            iconRes = R.drawable.ic_camera
        ),

        // CATEGORIA 8: LEMBRANÇAS E MUSEUS
        OlimpiaItem(
            id = "cat_museus",
            title = "Museus e Patrimônio Histórico",
            category = "HISTÓRIAS E MEMÓRIAS",
            subtitle = "O rico tesouro das tradições e memórias de Olímpia",
            description = "A história viva do Brasil e do interior paulista está preservada nos museus de Olímpia. Um circuito cultural que encanta visitantes curiosos sobre as origens do folclore e da formação da cidade.",
            highlights = "• Museu de História e Folclore Maria Olímpia: mais de 10.000 itens de arte popular brasileira\n• Museu de Cera Dreamland: cultura pop, celebridades e cinema mundial\n• Acervo e Memorial Prof. José Sant'anna no Recinto do Folclore\n• Exposições temporárias de artes plásticas na Casa da Cultura",
            tips = "• O Museu Maria Olímpia tem entrada gratuita e é uma aula viva sobre lendas, mitos e tradições brasileiras.",
            iconRes = R.drawable.ic_landmark
        )
    )

    fun findById(id: String): OlimpiaItem? {
        return items.find { it.id == id }
    }

    fun search(query: String): List<OlimpiaItem> {
        val q = query.trim().lowercase()
        if (q.isEmpty()) return emptyList()
        return items.filter { item ->
            item.title.lowercase().contains(q) ||
            item.category.lowercase().contains(q) ||
            item.subtitle.lowercase().contains(q) ||
            item.description.lowercase().contains(q)
        }
    }

    fun isFavorite(context: Context, id: String): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val favs = prefs.getStringSet(KEY_FAVORITES, emptySet()) ?: emptySet()
        return favs.contains(id)
    }

    fun toggleFavorite(context: Context, id: String): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val favs = (prefs.getStringSet(KEY_FAVORITES, emptySet()) ?: emptySet()).toMutableSet()
        val newState = if (favs.contains(id)) {
            favs.remove(id)
            false
        } else {
            favs.add(id)
            true
        }
        prefs.edit().putStringSet(KEY_FAVORITES, favs).apply()
        return newState
    }

    fun getFavoriteItems(context: Context): List<OlimpiaItem> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val favs = prefs.getStringSet(KEY_FAVORITES, emptySet()) ?: emptySet()
        return items.filter { favs.contains(it.id) }
    }
}
