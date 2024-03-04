package com.example.alne.repository

import com.example.alne.GlobalApplication
import com.example.alne.model.Comment
import com.example.alne.model.DeleteFavorite
import com.example.alne.model.UserId
import com.example.alne.room.model.recipe
import com.example.alne.Network.RecipeApi
import com.example.alne.Network.getRetrofit
import com.example.alne.model.requestComment

class recipeRepository {

    private val recipeService = getRetrofit().create(RecipeApi::class.java)

    fun getAllRecipe(): ArrayList<recipe> = GlobalApplication.appDatabase.recipeDao().getAll() as ArrayList<recipe>



//    fun getUsersComments(): Arra
    fun addUserComment(comment: Comment) = recipeService.addUserComment(comment)

    fun deleteUserComment(requestComment: requestComment) = recipeService.deleteUserComment(requestComment)

    //특정 레시피 조회
    fun getRecipeProcess(recipeCode: Int) = recipeService.getRecipeProcess(recipeCode)

    fun addRecipeFavorite(recipeCode: Int,userId: UserId) = recipeService.addRecipeFavorite(recipeCode,userId)

    fun deleteRecipeFavorite(delete: DeleteFavorite) = recipeService.deleteRecipeFavorite(delete)
}