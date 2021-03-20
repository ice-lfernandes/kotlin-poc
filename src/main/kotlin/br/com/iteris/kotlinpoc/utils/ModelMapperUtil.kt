package br.com.iteris.kotlinpoc.utils

import org.modelmapper.ModelMapper
import org.modelmapper.config.Configuration
import org.modelmapper.convention.MatchingStrategies

class ModelMapperUtil : ModelMapper(){

    init {
        configuration.matchingStrategy = MatchingStrategies.LOOSE
        configuration.fieldAccessLevel = Configuration.AccessLevel.PRIVATE
        configuration.isFieldMatchingEnabled = true
        configuration.isSkipNullEnabled = true
    }

}

object Mapper {
    val mapper = ModelMapperUtil()

    inline fun <S, reified T> convert(source: S): T = mapper.map(source, T::class.java)
}