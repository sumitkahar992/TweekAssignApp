package com.example.tweekassignapp.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tweekassignapp.R
import com.example.tweekassignapp.cat.CatScreen
import com.example.tweekassignapp.tools.DataProvider


data class Employee(val id: Int, val name: String)


@Preview(showBackground = true)
@Composable
fun NewsScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        var groceriesState by remember { mutableStateOf(listOf("Milk", "Water")) }

        GroceryInput { item ->
            groceriesState = groceriesState + listOf(item)
        }
        GroceryList(groceriesState)

        EmployeeDetails()

    }


}


@Composable
private fun GroceryInput(
    onGroceryItemAdded: (String) -> Unit
) {
    var textState by remember { mutableStateOf("") }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = textState,
        onValueChange = { textState = it },
        label = { Text(text = stringResource(R.string.new_item)) }
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                onGroceryItemAdded(textState)
                textState = ""
            }
        ) {
            Text(text = stringResource(R.string.add_item))
        }
    }
}


@Composable
private fun GroceryList(groceriesState: List<String>) {

    LazyColumn() {

        items(groceriesState.size) {
            Text(text = groceriesState[it])
        }


    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmployeeDetails() {
    val employeeList = remember {
        listOf(
            Employee(1, "A"),
            Employee(2, "B"),
            Employee(3, "C"),
            Employee(4, "D"),
            Employee(5, "E"),
            Employee(6, "F"),
            Employee(7, "G"),
            Employee(8, "H"),
            Employee(9, "I"),
            Employee(10, "J"),
            Employee(11, "K"),
        )
    }
    var empDetailsList by remember { mutableStateOf(employeeList) }

    LazyColumn {
        items(empDetailsList, key = { it.id }) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .animateItemPlacement(
                        tween(
                            durationMillis = 1000,
                            easing = LinearEasing
                        )
                    )
                    .clickable {
                        empDetailsList = empDetailsList.shuffled()
                    },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = it.id.toString())
                Text(text = it.name)

            }

        }


    }
}























