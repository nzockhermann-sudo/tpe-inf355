package com.example.todolist

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {

    private var nextId = 0

    var todoList = mutableStateListOf<TodoItem>()
        private set

    fun addTodo(title: String) {
        if (title.isNotBlank()) {
            todoList.add(TodoItem(nextId++, title))
        }
    }

    fun removeTodo(todo: TodoItem) {
        todoList.remove(todo)
    }

    fun toggleTodo(todo: TodoItem) {
        todo.isDone = !todo.isDone
    }
}
