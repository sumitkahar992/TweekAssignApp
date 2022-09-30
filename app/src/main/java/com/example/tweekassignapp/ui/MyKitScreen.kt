package com.example.tweekassignapp.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tweekassignapp.R
import com.example.tweekassignapp.tools.DataProvider
import com.example.tweekassignapp.tools.Ext.color
import com.example.tweekassignapp.tools.Ext.round

@Composable
fun MyKitScreen() {

    val person = remember {
        DataProvider.tweetList
    }
    val scrollState = rememberLazyGridState()

    Box {
        Column {
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
