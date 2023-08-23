package ir.codroid.batmanmovies.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import ir.codroid.batmanmovies.data.model.MovieDetail

class RatingTypeConverter {


    @TypeConverter
    fun convertToJsonString(ratingList: List<MovieDetail.Rating>?): String {
        val gson = Gson()
        return gson.toJson(ratingList)
    }

    @TypeConverter
    fun convertToObject(json: String): List<MovieDetail.Rating>? {
        val gson = Gson()
        return listOf(gson.fromJson(json, MovieDetail.Rating::class.java))
    }

}