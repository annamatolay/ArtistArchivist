package dev.anmatolay.artistarchivist.util

import dev.anmatolay.artistarchivist.core.model.Artist

object MockArtist {
    val dummy = Artist()
    val minimal = Artist(name = "Nirvana", type = "Group")
    val withoutDetails = minimal.copy(
        gender = "other",
        sortName = "Nirvana",
        country = "US",
        tags = listOf(
            Artist.Tag(count = 15, name = "rock"),
            Artist.Tag(count = 16, name = "alternative rock"),
            Artist.Tag(count = 2, name = "90s"),
            Artist.Tag(count = 2, name = "punk"),
            Artist.Tag(count = 8, name = "american"),
            Artist.Tag(count = 1, name = "punk rock"),
        )
    )

    val withDetails = withoutDetails.copy(
        gender = "non-binary",
        isnis = listOf("0000000123486830", "0000000123487390"),
        area = Artist.Area(name = "United States", type = "Country"),
        beginArea = Artist.BeginArea(name = "Aberdeen", type = "City"),
        lifeSpan = Artist.LifeSpan("1988-01", "1994-04-05", true),
        aliases = listOf(
            Artist.Aliase(name = "ニルヴァーナ"),
            Artist.Aliase(name = "Nirvana"),
            Artist.Aliase(name = "Nirvana US")
        ),
        disambiguation = "90s US grunge band",
        tags = withoutDetails.tags?.plus(
            listOf(
                Artist.Tag(count = 1, name = "experimental"),
                Artist.Tag(count = 5, name = "seattle"),
                Artist.Tag(count = 41, name = "grunge"),
                Artist.Tag(count = 0, name = "band"),
                Artist.Tag(count = 2, name = "usa"),
                Artist.Tag(count = 0, name = "alternative"),
                Artist.Tag(count = 0, name = "legendary"),
                Artist.Tag(count = 0, name = "kurt cobain"),
                Artist.Tag(count = 0, name = "90"),
            )
        )
    )
}
