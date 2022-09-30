package com.example.tweekassignapp


fun main(){



    val string = "0042"
    val int: Int? = string.toInt()
    println(int)

    val string2 = "00012"
    string2.substring(4)
    println(string2)







/*
    radix 2 - alphabet 0, 1 (binary number)
    radix 8 - alphabet 0, 1, 2, 3, 4, 5, 6, 7 (octal number)
    radix 10 - decimal number


 */

    val istr = "1111"                        // 1 * 2^3 +   8
    val int3: Int? = istr.toInt(2)     // 1 * 2^2 +   4
    println(int3)                           //  1 * 2^1 +   2
                                            //  1 * 2^0     1  =  15
}