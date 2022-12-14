package com.example.tweekassignapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun AccountScreen(navController: NavController) {


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = { navController.navigate(Screen.List100.route) }) {
            Text(text = "I T E M L I S T 100")
        }

        Button(onClick = { navController.navigate(Screen.Cat.route) }) {
            Text(text = "C A T S C R E E N")
        }


    }

}


@Composable
fun ItemsList100() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        val list = (1..100).map { it.toString() }
        val listState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()



        LazyColumn(
            state = listState,
            modifier = Modifier
                .background(Color(0xFFFFFFFF))
                .wrapContentHeight()
                .fillMaxWidth(),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(list) { txt ->
                Card(
                    backgroundColor = Color(0xFF9ACD32),
                    modifier = Modifier
                        .fillMaxWidth(),
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = txt,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }


        }

        val isTopButtonVisible = remember {
            derivedStateOf {
                listState.firstVisibleItemIndex > 0
            }
        }

        FloatingActionButton(
            onClick = {
                coroutineScope.launch {
                    if (isTopButtonVisible.value) {
                        listState.animateScrollToItem(0)
                    } else {
                        listState.animateScrollToItem(list.size)
                    }
                }
            },
            shape = RoundedCornerShape(50),
            backgroundColor = Color(0xFFFF55A3),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 60.dp, end = 12.dp)

        ) {
            if (isTopButtonVisible.value) {
                Icon(Icons.Outlined.KeyboardArrowDown, "")
            } else {
                Icon(Icons.Outlined.KeyboardArrowUp, "")
            }
        }
    }
}

