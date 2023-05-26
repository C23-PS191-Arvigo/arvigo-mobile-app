package id.arvigo.arvigobasecore.data.repository.mapping

import id.arvigo.arvigobasecore.data.source.network.response.LoginResponse

//fun LoginResponse?.mappingToUseCaseEntity() :LoginResponse {
//    val newList : MutableList<Meals> = mutableListOf()
//
//    this?.forEach {
//        newList.add(
//            Meals(
//                id = it.idMeal,
//                name = it.strMeal,
//                image = it.strMealThumb
//            )
//        )
//    }
//    return if (this.isNullOrEmpty()) {
//        emptyList()
//    } else {
//        newList
//    }
//}