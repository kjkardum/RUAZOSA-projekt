package hr.fer.ruazosa.kviz2022.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import hr.fer.ruazosa.kviz2022.repository.DemoDataRepositoryImpl
import hr.fer.ruazosa.kviz2022.repository.FollowersRepositoryImpl
import hr.fer.ruazosa.kviz2022.repository.GameRepositoryImpl
import hr.fer.ruazosa.kviz2022.repository.UserRepositoryImpl
import hr.fer.ruazosa.kviz2022.repository.interfaces.DemoDataRepository
import hr.fer.ruazosa.kviz2022.repository.interfaces.FollowersRepository
import hr.fer.ruazosa.kviz2022.repository.interfaces.GameRepository
import hr.fer.ruazosa.kviz2022.repository.interfaces.UserRepository

@InstallIn(ViewModelComponent::class)
@Module
abstract class GameRepositoryModule {
    @Binds
    abstract fun getGameSource(repository: GameRepositoryImpl): GameRepository
}

@InstallIn(ViewModelComponent::class)
@Module
abstract class DemoDataRepositoryModule {
    @Binds
    abstract fun getDemoDataSource(repository: DemoDataRepositoryImpl): DemoDataRepository
}

@InstallIn(ViewModelComponent::class)
@Module
abstract class UserRepositoryModule {
    @Binds
    abstract fun getUserSource(repository: UserRepositoryImpl): UserRepository
}

@InstallIn(ViewModelComponent::class)
@Module
abstract class FollowersRepositoryModule {
    @Binds
    abstract fun getFollowersSource(repository: FollowersRepositoryImpl): FollowersRepository
}
