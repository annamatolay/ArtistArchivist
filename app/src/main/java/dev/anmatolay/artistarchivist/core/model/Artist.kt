package dev.anmatolay.artistarchivist.core.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.anmatolay.artistarchivist.util.extension.isNotNullOrBlank

// Generated with JsonToKotlinClass plugin
// https://plugins.jetbrains.com/plugin/9960-json-to-kotlin-class-jsontokotlinclass-
// And default values provided based on
// https://musicbrainz.org/doc/Artist AND https://musicbrainz.org/doc/Style/Artist
@JsonClass(generateAdapter = true)
data class Artist(
    val aliases: List<Aliase>? = null,
    val area: Area? = null,
    @Json(name = "begin-area")
    val beginArea: BeginArea? = null,
    val country: String? = null,
    val disambiguation: String? = null,
    val gender: String = "Not applicable",
    @Json(name = "gender-id")
    val genderId: String? = null,
    val id: String = "",
    val isnis: List<String>? = null,
    @Json(name = "life-span")
    val lifeSpan: LifeSpan = LifeSpan(),
    val name: String = "Unknown",
    val score: Int = 0,
    @Json(name = "sort-name")
    val sortName: String? = null,
    val tags: List<Tag>? = null,
    val type: String = "Other",
    @Json(name = "type-id")
    val typeId: String? = null,
) {
    val origin = buildString {
        val cityText = if (beginArea?.type == "City") beginArea.name else null
        val areaText = if (area?.type == "Country") area.name else null
        val countryText = country ?: ""

        if (cityText.isNotNullOrBlank()) append("$cityText - ")
        if (areaText.isNotNullOrBlank()) append("$areaText - ")
        append(countryText)
    }
    @JsonClass(generateAdapter = true)
    data class Aliase(
        @Json(name = "begin-date")
        val beginDate: String? = null,
        @Json(name = "end-date")
        val endDate: String? = null,
        val locale: String? = null,
        val name: String = "",
        val primary: Boolean? = null,
        @Json(name = "sort-name")
        val sortName: String = "",
        val type: String? = null,
        @Json(name = "type-id")
        val typeId: String? = null,
    )

    @JsonClass(generateAdapter = true)
    data class Area(
        val id: String = "",
        @Json(name = "life-span")
        val lifeSpan: LifeSpan = LifeSpan(),
        val name: String = "",
        @Json(name = "sort-name")
        val sortName: String = "",
        val type: String = "",
        @Json(name = "type-id")
        val typeId: String = "",
    ) {
        @JsonClass(generateAdapter = true)
        data class LifeSpan(
            val begin: String? = null,
            val end: String? = null,
            val ended: Boolean? = null,
        )
    }

    @JsonClass(generateAdapter = true)
    data class BeginArea(
        val id: String = "",
        @Json(name = "life-span")
        val lifeSpan: LifeSpan = LifeSpan(),
        val name: String = "",
        @Json(name = "sort-name")
        val sortName: String = "",
        val type: String = "",
        @Json(name = "type-id")
        val typeId: String = "",
    ) {
        @JsonClass(generateAdapter = true)
        data class LifeSpan(
            val begin: String? = null,
            val ended: Boolean? = null,
        )
    }

    @JsonClass(generateAdapter = true)
    data class LifeSpan(
        val begin: String? = null,
        val end: String? = null,
        val ended: Boolean? = null,
    )

    @JsonClass(generateAdapter = true)
    data class Tag(
        val count: Int = 0,
        val name: String = "",
    )
}
