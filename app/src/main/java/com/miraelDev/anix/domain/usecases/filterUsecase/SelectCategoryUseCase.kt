package com.miraelDev.anix.domain.usecases.filterUsecase

import com.miraelDev.anix.domain.models.CategoryModel
import com.miraelDev.anix.domain.repository.FilterAnimeRepository
import javax.inject.Inject

class SelectCategoryUseCase @Inject constructor(val repository: FilterAnimeRepository) {
    suspend operator fun invoke(categoryId:Int, category: String) =
        repository.selectCategory(categoryId, category)
}