package com.miraelDev.vauma.domain.usecases.filterUsecase

import com.miraelDev.vauma.domain.repository.FilterAnimeRepository
import javax.inject.Inject

class SelectCategoryUseCase @Inject constructor(val repository: FilterAnimeRepository) {
    suspend operator fun invoke(categoryId:Int, category: String) =
        repository.selectCategory(categoryId, category)
}