package hr.fer.ruazosa.kviz2022.repository.interfaces

import androidx.lifecycle.LiveData
import hr.fer.ruazosa.kviz2022.network.DTOs.DemoApiDTO
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface DemoDataRepository {
    suspend fun getDemoUserAsync(userId: Int): DemoApiDTO

}