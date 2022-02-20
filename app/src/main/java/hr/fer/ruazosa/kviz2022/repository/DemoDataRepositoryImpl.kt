package hr.fer.ruazosa.kviz2022.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hr.fer.ruazosa.kviz2022.network.DTOs.DemoApiDTO
import hr.fer.ruazosa.kviz2022.network.NetworkServices
import hr.fer.ruazosa.kviz2022.network.RemoteDemoApiService
import hr.fer.ruazosa.kviz2022.repository.interfaces.DemoDataRepository
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class DemoDataRepositoryImpl @Inject constructor(
    var remoteDemoApiService: RemoteDemoApiService
) : DemoDataRepository {
    override suspend fun getDemoUserAsync(userId: Int): DemoApiDTO {
        return remoteDemoApiService.getDemoUser(2)
    }
}