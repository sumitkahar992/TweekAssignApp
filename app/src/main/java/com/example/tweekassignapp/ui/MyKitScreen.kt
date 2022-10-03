package com.example.tweekassignapp.ui

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tweekassignapp.R
import com.example.tweekassignapp.tools.Ext.color
import com.example.tweekassignapp.tools.Ext.round
import com.example.tweekassignapp.viewmodel.TweekViewModel
import kotlin.text.Typography

@Composable
fun MyKitScreen() {

    val viewModel: TweekViewModel = hiltViewModel()


    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
        ) {
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

            Divider()



//                element.forEach{
//                   Text(text = "Number : ${it}")
//                }

            viewModel.cards.forEach {
                SimpleCard(
                    id = it.id,
                    image = it.image,
                    name = it.developer)
            }


        }

        val context = LocalContext.current

        FloatingActionButton(
            modifier = Modifier
                .padding(vertical = 70.dp, horizontal = 16.dp)
                .align(Alignment.BottomEnd),
            onClick = {
                viewModel.generateRandomCard()

                viewModel.cards.forEach{
                    Toast.makeText(context,"Item Added  - ${it.id}", Toast.LENGTH_SHORT).show()
                }

        }) {}


    }


}

@Composable
fun SimpleCard(
    id: Int,
    image: Int,
    name: String
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CreateImageProfile(image = image)

            Spacer(modifier = Modifier.width(16.dp))

            Text(text = name, modifier = Modifier.weight(1f), style = MaterialTheme.typography.h6 )

            Spacer(modifier = Modifier.width(140.dp))

            Box(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .size(40.dp)
                    .round(null, 100)
                    .color(Color.Blue)

            ) {
                Text(
                    text = "$id",
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
