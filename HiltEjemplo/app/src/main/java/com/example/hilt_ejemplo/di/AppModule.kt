package com.example.hilt_ejemplo.di

import com.example.hilt_ejemplo.data.RepositorioSimulado
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module // indica que la clase es un Modulo de Hilt
@InstallIn(SingletonComponent::class) // indica en que nivel estará disponible la dependencia, con SingletonComponent le decimos que en toda la aplicacion.
object AppModule { // usado para no crear instancias manualmente
    @Provides // creamos y damos a hilt la instancia(funcion) a utilizar
    @Singleton // le dice a hilt que esta instancia debe ser unica
    fun proveerRepositorio(): RepositorioSimulado{
        return RepositorioSimulado() // instancia a retornar
    }

}

/*
* SingletonComponent = equivalente a un refrigerador,
* todoo lo guardado aqui estará disponible mientras
*  la casa este abierta (app encendida).
*
* @Singleton = es una botella de agua en el refrigerador,
* si se requiere se toma esta en lugar de crear multiples
* botellas cada vez que se requiera una.
*
* */