package com.example.todolist

data class TodoItem(
    val id: Int,
    val title: String,
    var isDone: Boolean = false
)
