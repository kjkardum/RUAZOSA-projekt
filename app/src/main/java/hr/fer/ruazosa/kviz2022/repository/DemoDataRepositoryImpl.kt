package hr.fer.ruazosa.kviz2022.repository

import hr.fer.ruazosa.kviz2022.network.dto.DemoApiDTO
import hr.fer.ruazosa.kviz2022.network.RemoteDemoApiService
import hr.fer.ruazosa.kviz2022.repository.interfaces.DemoDataRepository
import javax.inject.Inject

class DemoDataRepositoryImpl @Inject constructor(
    var remoteDemoApiService: RemoteDemoApiService
) : DemoDataRepository {
    override suspend fun getDemoUserAsync(userId: Int): DemoApiDTO {
        return remoteDemoApiService.getDemoUser(2)
    }
}