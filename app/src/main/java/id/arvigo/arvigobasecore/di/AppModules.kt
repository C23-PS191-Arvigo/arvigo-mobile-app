package id.arvigo.arvigobasecore.di

import id.arvigo.arvigobasecore.data.repository.DefaultAuthRepository
import id.arvigo.arvigobasecore.data.repository.PersonalityRepository
import id.arvigo.arvigobasecore.data.source.local.AuthPreferences
import id.arvigo.arvigobasecore.data.source.network.ApiService
import id.arvigo.arvigobasecore.domain.repository.AuthRepository
import id.arvigo.arvigobasecore.domain.usecase.LoginUseCase
import id.arvigo.arvigobasecore.ui.feature.login.LoginViewModel
import id.arvigo.arvigobasecore.ui.feature.personality.PersonalityViewModel
import id.arvigo.arvigobasecore.ui.feature.register.RegisterViewModel
import id.arvigo.arvigobasecore.util.Constant.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.compose.BuildConfig
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        val loggingInterceptor =
            HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val viewModelModules = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { PersonalityViewModel(get()) }
}

val useCaseModule = module {
    single { LoginUseCase(get()) }
    single<AuthRepository> { DefaultAuthRepository() }
    single<PersonalityRepository> {
        PersonalityRepository(get())
    }
}

val dataPreferencesModule = module {
    single { AuthPreferences(get()) }
}

