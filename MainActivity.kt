package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = TodoViewModel()
            MaterialTheme {
                TodoScreen(viewModel)
            }
        }
    }
}

@Composable
fun TodoScreen(viewModel: TodoViewModel) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Nouvelle tâche") }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                viewModel.addTodo(text)
                text = ""
            }) {
                Text("Ajouter")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(viewModel.todoList) { todo ->
                TodoRow(todo, viewModel)
            }
        }
    }
}

@Composable
fun TodoRow(todo: TodoItem, viewModel: TodoViewModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {

        Checkbox(
            checked = todo.isDone,
            onCheckedChange = { viewModel.toggleTodo(todo) }
        )

        Text(
            text = todo.title,
            modifier = Modifier.weight(1f),
            textDecoration = if (todo.isDone)
                TextDecoration.LineThrough
            else TextDecoration.None
        )

        Button(onClick = { viewModel.removeTodo(todo) }) {
            Text("X")
        }
    }
}
