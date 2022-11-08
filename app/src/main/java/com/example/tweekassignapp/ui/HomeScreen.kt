package com.example.tweekassignapp.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tweekassignapp.R
import com.example.tweekassignapp.data.model.MockyModelItem
import com.example.tweekassignapp.viewmodel.TweekViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeContent() {

    HomeContentScreen()

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeContentScreen() {

    val viewModel: TweekViewModel = hiltViewModel()

    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()


    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )


    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = { BottomSheet() },
        modifier = Modifier.fillMaxSize()
    ) {

        val isTopButtonVisible = remember {
            derivedStateOf {
                listState.firstVisibleItemIndex > 0
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (viewModel.snapshotStateList.isEmpty()) {
                LoadingState()
            } else {

                Box {

                    MainContent(
                        playerList = viewModel.snapshotStateList,
                        listState = listState,
                        score = ""
                    )


                    FloatingActionButton(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(horizontal = 16.dp, vertical = 57.dp),
                        onClick = {
                            coroutineScope.launch {
                                if (isTopButtonVisible.value) {
                                    listState.animateScrollToItem(0)
                                } else {
                                    listState.animateScrollToItem(viewModel.snapshotStateList.size - 1)
                                }
                            }
                        },

                        ) {
                        if (isTopButtonVisible.value) {
                            Icon(
                                imageVector = Icons.Rounded.KeyboardArrowUp,
                                contentDescription = ""
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Rounded.KeyboardArrowDown,
                                contentDescription = ""
                            )
                        }
                    }

                    FloatingActionButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomEnd),
                        shape = RectangleShape,
                        backgroundColor = Color.White,
                        contentColor = Color.DarkGray,
                        onClick = {
                            coroutineScope.launch {
                                if (sheetState.isVisible) sheetState.hide()
                                else sheetState.show()
                            }
                        }) {
                        SortBycard()


                    }


                }

            }

        }
    }


}

@Composable
fun LoadingState() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(color = Color.Magenta)
        Text(text = "L o a d i n g   -_=")
    }
}


@Composable
fun MainContent(playerList: List<MockyModelItem>, listState: LazyListState, score: String) {

    LazyColumn(
        state = listState,
        modifier = Modifier.padding(bottom = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            items = playerList,
            itemContent = {
                PlayerCard(player = it, score = score)
            }
        )

    }


}


@Composable
fun AppBar() {

    TopAppBar(
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null
                    )
                }

                Text(text = "Leaderboards", modifier = Modifier.align(Alignment.CenterVertically))

                Spacer(modifier = Modifier.width(150.dp))

                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = null
                    )
                }
            }
        }

    )
}


@Composable
fun BottomBar(
    navController: NavController,
    bottomBarState: Boolean
) {
    val items = listOf(
        BottomNavItem.News,
        BottomNavItem.MyKits,
        BottomNavItem.Home,
        BottomNavItem.Leaderboard,
        BottomNavItem.Account
    )

    AnimatedVisibility(
        visible = bottomBarState,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                BottomNavigation(
                    backgroundColor = Color.White
                ) {
                    items.forEach { item ->
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    imageVector = item.image,
                                    contentDescription = item.title
                                )
                            },
                            label = { Text(text = item.title, maxLines = 1) },
                            selected = currentRoute == item.route,
                            onClick = {
                                navController.navigate(item.route)
                            },
                            selectedContentColor = Color.Blue,
                            unselectedContentColor = Color.Gray,
                            alwaysShowLabel = false
                        )
                    }
                }
            }
        }
    )
}


@Composable
fun PlayerCard(player: MockyModelItem, score: String) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(13.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(11.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = player.id.toInt().toString(),
                fontSize = 22.sp,
                fontFamily = FontFamily.Monospace

            )

            CreateImageProfile()

            Text(text = player.name, modifier = Modifier.weight(1f))

            Spacer(modifier = Modifier.width(30.dp))

            Text(
                text = player.score.toString(),
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace,
                maxLines = 1,
                textAlign = TextAlign.End
            )
        }
    }


}


@Composable
fun CreateImageProfile(modifier: Modifier = Modifier, image: Int = R.drawable.profile_image2) {
    Surface(
        modifier = Modifier
            .size(75.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "profile image",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )

    }
}


@Preview
@Composable
fun AppBarPrev() {
    AppBar()
}

@Preview
@Composable
fun BottomBarPrev() {
    val navController = rememberNavController()
    val bottomBarState = true
    BottomBar(navController = navController, bottomBarState = bottomBarState)
}



























































