package com.example.tweekassignapp.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    val tweekViewModel: TweekViewModel = hiltViewModel()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (tweekViewModel.snapshotStateList.isEmpty()) {
            LoadingState()
        } else {

            MainContent(playerList = tweekViewModel.snapshotStateList)

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
fun MainContent(
    playerList: SnapshotStateList<MockyModelItem>,
    paddingValues: PaddingValues = PaddingValues()
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val isTopButtonVisible = remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }


    LazyColumn(
        state = listState,
        modifier = Modifier.padding(bottom = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            items = playerList,
            itemContent = {
                PlayerCard(tweekModel = it)
            }
        )



        item {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        if (isTopButtonVisible.value) {
                            listState.animateScrollToItem(0)
                        } else {
                            listState.animateScrollToItem(playerList.size - 1)
                        }
                    }
                },

                ) {
                if (isTopButtonVisible.value) {
                    Icon(Icons.Outlined.Refresh, "")
                } else {
                    Icon(Icons.Outlined.Refresh, "")
                }


            }


        }
    }


}



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottoSheetTwo() {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Text(text = "Hello from sheet")
            }
        }, sheetPeekHeight = 0.dp
    ) {
        Button(onClick = {
            coroutineScope.launch {

                if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                } else {
                    bottomSheetScaffoldState.bottomSheetState.collapse()
                }
            }
        }) {
            Text(text = "Expand/Collapse Bottom Sheet")
        }
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
fun PlayerCard(tweekModel: MockyModelItem) {


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
                text = tweekModel.id.toInt().toString(),
                fontSize = 22.sp,
                fontFamily = FontFamily.Monospace

            )

            CreateImageProfile()

            Text(text = tweekModel.name, modifier = Modifier.weight(1f))

            Spacer(modifier = Modifier.width(30.dp))

            Text(
                text = tweekModel.score.toString(),
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace,
                maxLines = 1,
                textAlign = TextAlign.End
            )
        }
    }


}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
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
            painter = painterResource(id = R.drawable.profile_image2),
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



























































