package kr.ac.kumoh.s20220382.s24w1403retrofit


import retrofit2.http.GET
import retrofit2.http.Query

interface SongApi {

    @GET("song")
    suspend fun getSongs(
        @Query("apikey") apiKey: String = SongApiConfig.API_KEY
    ): List<Song>

}