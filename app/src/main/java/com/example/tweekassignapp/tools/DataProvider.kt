package com.example.tweekassignapp.tools

import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import com.example.tweekassignapp.R

object DataProvider {

    val tweet = Tweet(
        1,
        "Jetpack compose is the next thing for andorid. Declarative UI is the way to go for all screens.",
        "The Verge",
        "@verge",
        "12m",
        R.drawable.img1,
        100,
        12,
        15,
        "Twitter for web"
    )

    val tweetList = listOf(
        tweet.copy(
            id = 1,
            text = "Google",
            tweetImageId = R.drawable.img1
        ),
        tweet.copy(
            id = 2,
            text = "The Verge",
            tweetImageId = R.drawable.img2
        ),
        tweet.copy(
            id = 3,
            text = "Amazon",
            tweetImageId = R.drawable.img3
        ),
        tweet.copy(
            id = 4,
            text = "Facebook",
            tweetImageId = R.drawable.img4
        ),
        tweet.copy(
            id = 5,
            text = "Instagram",
            tweetImageId = R.drawable.img5
        ),
        tweet.copy(
            id = 6,
            text = "Twitter",
            tweetImageId = R.drawable.img6
        ),
        tweet.copy(
            id = 7,
            text = "Netflix",
            tweetImageId = R.drawable.img7
        ),
        tweet.copy(
            id = 8,
            text = "Tesla",
            tweetImageId = R.drawable.img8
        ),
        tweet.copy(
            id = 9,
            text = "Microsoft",
            tweetImageId = R.drawable.img9
        ),
        tweet.copy(
            id = 10,
            text = "Tencent",
            tweetImageId = R.drawable.img10
        ),
        tweet.copy(
            id = 11,
            text = "Snapchat",
            tweetImageId = R.drawable.img11
        ),
        tweet.copy(
            id = 12,
            text = "Tiktok",
            tweetImageId = R.drawable.img12
        ),
        tweet.copy(
            id = 13,
            text = "Samsung",
            tweetImageId = R.drawable.img13
        ),
        tweet.copy(
            id = 14,
            text = "Youtube",
            tweetImageId = R.drawable.img14
        ),
        tweet.copy(
            id = 15,
            text = "Gmail",
            tweetImageId = R.drawable.img15
        ),
        tweet.copy(
            id = 16,
            text = "Android",
            tweetImageId = R.drawable.img16
        ),
        tweet.copy(
            id = 17,
            text = "Whatsapp",
            tweetImageId = R.drawable.img17
        ),
        tweet.copy(
            id = 18,
            text = "Telegram",
            tweetImageId = R.drawable.img18
        ),
        tweet.copy(
            id = 19,
            text = "Spotify",
            tweetImageId = R.drawable.img19
        ),
        tweet.copy(
            id = 20,
            text = "Disney",
            tweetImageId = R.drawable.img20
        ),

        ).map { DrawableStringPair(it.id, it.text, it.tweetImageId) }


    data class DrawableStringPair(
        @IntegerRes val idd: Int,
        val textt: String,
        @DrawableRes val imagee: Int
    )


}




















