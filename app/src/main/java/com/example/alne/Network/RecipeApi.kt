package com.example.alne.Network

import com.example.alne.model.Comment
import com.example.alne.model.DeleteFavorite
import com.example.alne.model.FavoriteRespond
import com.example.alne.model.LikeRespond
import com.example.alne.model.RecipeProcessRespond
import com.example.alne.model.UserId
import com.example.alne.model.Status
import com.example.alne.model.requestComment
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface RecipeApi {

    @GET("/recipe")
    fun getAllRecipe(): Call<RecipeResponse>

    @POST("/addComment")
    fun addUserComment(
        @Body comment: Comment
    ): Call<AuthResponse>

    @POST("/recipe/{recipeCode}")
    fun getRecipeProcess(
        @Path("recipeCode") recipeCode: Int,
        @Body userId: UserId
    ): Call<RecipeProcessRespond>

    @POST("/recipe/{recipeCode}/favorite")
    fun addRecipeFavorite(
        @Path("recipeCode") recipeCode: Int,
        @Body userId: UserId
    ): Call<FavoriteRespond>

    @POST("/favorites/delete")
    fun deleteRecipeFavorite(
        @Body delete: DeleteFavorite
    ): Call<Status>

    @POST("/recipe/{recipeCode}/like")
    fun likeRecipe(
        @Path("recipeCode") recipeCode: Int,
        @Body userid: UserId
    ): Call<LikeRespond>

    @POST("/delComment")
    fun deleteUserComment(
        @Body requestComment: requestComment
    ): Call<AuthResponse>




}