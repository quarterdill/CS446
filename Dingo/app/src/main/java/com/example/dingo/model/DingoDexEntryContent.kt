package com.example.dingo.model

import android.content.Context
import com.example.dingo.common.SingletonHolder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

// Achievements are given in the assets/achievements.json folder
// This is an array where each element has the following format:
// {
//    "name": "Dingo Diver",
//    "description": "Make a Dingo account",
//    "conditions": [{"field": "Logins", "value": 1}]
// }
// it will be activated once the condition fields have at least the specified value
// retrieved from the user's stats
data class DingoDexEntryContent (
    var id: Int = 0,
    var is_fauna: Boolean = false,
    var name: String = "",
    var scientific_name: String = "",
    var description: String = "",
    var default_picture_name: String = "",
)
object DingoDexScientificToIndex {
    val dingoDexFaunaScientificToIndex: MutableMap<String, Int> = mutableMapOf()
    val dingoDexFloraScientificToIndex: MutableMap<String, Int> = mutableMapOf()
}


object DingoDexEntryListings {
    val floraEntryList: MutableList<DingoDexEntryContent> = mutableListOf()
    val faunaEntryList: MutableList<DingoDexEntryContent> = mutableListOf()
    var dingoDexEntryList: List<DingoDexEntryContent> = emptyList()
}
