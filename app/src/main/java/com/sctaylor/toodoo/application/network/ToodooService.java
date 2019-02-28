package com.sctaylor.toodoo.application.network;


import com.sctaylor.toodoo.models.TodoItem;
import com.sctaylor.toodoo.models.TodoItemUpdate;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by simon on 6/27/2018.
 */

public interface ToodooService {

    @GET("todo")
    Single<List<TodoItem>> getTodos();

    @GET("todo/{id}")
    Single<TodoItem> getTodoItem(@Path("id") int id);

    @POST("todo")
    Single<TodoItem> createTodoItem(@Body TodoItemUpdate item);

    @PUT("todo/{id}")
    Completable updateTodoItem(@Path("id") int id, @Body TodoItemUpdate item);

}
