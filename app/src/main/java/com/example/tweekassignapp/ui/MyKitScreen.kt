package com.example.tweekassignapp.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tweekassignapp.R
import com.example.tweekassignapp.cat.SimpleData
import com.example.tweekassignapp.data.model.MockyModelItem
import com.example.tweekassignapp.tools.Ext.color
import com.example.tweekassignapp.tools.Ext.round
import com.example.tweekassignapp.viewmodel.TweekViewModel

@Composable
fun MyKitScreen() {
    val viewModel: TweekViewModel = hiltViewModel()

    SimpleScreen(list = viewModel.cards)


}


@Composable
private fun SimpleScreen(
    list: List<SimpleData>
) {
    val viewModel: TweekViewModel = hiltViewModel()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.generateRandomCard()
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }



    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {

            item {
                TopBar(
                    navigationIcon = {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                                .size(40.dp)
                                .round(null, 100)
                                .color(Color.Blue)

                        ) {
                            Text(
                                text = "V",
                                modifier = Modifier.align(Alignment.Center),
                                fontSize = 29.sp,
                                color = Color.White
                            )

                        }
                    },
                    title = {
                        Text(
                            modifier = Modifier
                                .weight(3f)
                                .padding(10.dp),
                            text = "Your Library"

                        )
                    },
                    actions = {
                        IconBtn(resIcon = R.drawable.sortby, modifier = Modifier.size(25.dp))
                        IconBtn(resIcon = R.drawable.ic_search)
                    }
                )

            }

            items(list) {
                SimpleCard(list = it)
            }
            
            item { 
                Button(onClick = {
                    viewModel.sortBy()
                }) {
                    Text(text = "Nothing OS")
                }
            }
            
            items(viewModel.snapshotStateList){
                Row {
                   Text(text = it.bfc.toString()) 
                }
            }

        }


    }
}





@Composable
fun SimpleCard(list: SimpleData) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CreateImageProfile(image = list.image)

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = list.developer,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h6
            )

            Spacer(modifier = Modifier.width(140.dp))

            Box(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .size(40.dp)
                    .round(null, 100)
                    .color(Color.Blue)

            ) {
                Text(
                    text = list.id.toString(),
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 17.sp,
                    color = Color.White
                )
            }

        }
    }
}


@Composable
fun CardColumn(
    @DrawableRes image: Int,
    text: String,
    id: Int

) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape),
            painter = painterResource(id = image),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )


        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "$text -> $id", fontSize = 11.sp, fontWeight = FontWeight(3))

    }

}


@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    navigationIcon: @Composable (() -> Unit)? = null,
    title: @Composable (RowScope.() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = verticalAlignment
    ) {
        navigationIcon?.invoke()
        title?.invoke(this)
        actions?.invoke(this)
    }

}

@Composable
fun IconBtn(
    resIcon: Int,
    modifier: Modifier = Modifier,
    tint: Color = Color.White,
    selected: Boolean = true,
    selectedIcon: Int = resIcon,
    onClick: () -> Unit = {},
) {
    IconButton(modifier = modifier, onClick = onClick) {
        Icon(
            painter = if (selected) {
                painterResource(id = selectedIcon)
            } else {
                painterResource(id = resIcon)
            },
            contentDescription = null,
            tint = tint
        )
    }
}
