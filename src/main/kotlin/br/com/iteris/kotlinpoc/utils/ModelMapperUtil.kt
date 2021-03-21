package br.com.iteris.kotlinpoc.utils

import org.modelmapper.ModelMapper
import org.modelmapper.config.Configuration
import org.modelmapper.convention.MatchingStrategies

/**
 * Classe Mapper Utiliario que extende @ModelMapper para fazer mapeamento de objetos
 */
class ModelMapperUtil : ModelMapper(){

    init {
        configuration.matchingStrategy = MatchingStrategies.LOOSE
        configuration.fieldAccessLevel = Configuration.AccessLevel.PRIVATE
        configuration.isFieldMatchingEnabled = true
        configuration.isSkipNullEnabled = true
    }

}

/**
 * Objeto estático que será chamado quando precisar realizar o mapeamento
 */
object Mapper {
    val mapper = ModelMapperUtil()

    /**
     * Método Map
     *
     * @param source origem do objeto que deverá ser transformado
     * @param T Tipo do Objeto de destino da transformação
     */
    inline fun <S, reified T> convert(source: S): T = mapper.map(source, T::class.java)
}