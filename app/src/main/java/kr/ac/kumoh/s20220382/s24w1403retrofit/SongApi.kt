package kr.ac.kumoh.s20220382.s24w1403retrofit


import retrofit2.http.GET
import retrofit2.http.Query

interface SongApi {
    interface SongApi {
        @GET("song")
        suspend fun getSongs(
            @Query("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inl1YXZ4Ympsd3d1bWNqa3R6a2NoIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzMzMDEyNDAsImV4cCI6MjA0ODg3NzI0MH0.K4HvS7yL1qf4RG7VH7RjRZtNppuGw_YjsGJqBe3HswA") apiKey: String = SongApiConfig.API_KEY
        ): List<Song>
    }
}