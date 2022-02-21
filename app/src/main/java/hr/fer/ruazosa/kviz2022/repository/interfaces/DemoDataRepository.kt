package hr.fer.ruazosa.kviz2022.repository.interfaces

import hr.fer.ruazosa.kviz2022.network.dto.DemoApiDTO

interface DemoDataRepository {
    suspend fun getDemoUserAsync(userId: Int): DemoApiDTO

}