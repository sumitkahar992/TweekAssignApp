package com.example.tweekassignapp.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

object Ext {


    fun Modifier.round(
        radius: Dp? = null,
        percent: Int = 0
    ): Modifier {
        return if (radius != null) {
            this.clip(RoundedCornerShape(radius))
        } else {
            this.clip(RoundedCornerShape(percent))
        }
    }





    fun Modifier.color(
        color: Color = Color.Transparent
    ): Modifier {
        return this.background(color)
    }



}