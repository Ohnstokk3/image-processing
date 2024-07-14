package com.example.shadder.ui.theme

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import com.example.shadder.R

@Composable
fun test() {
    val context = LocalContext.current
    val image = BitmapFactory.decodeResource(context.resources, R.drawable.apps_11001_14495311847124170_004a386e_eb07_4903_b17d_bba1cc3df02e)

    val newBitmap = Bitmap.createBitmap(image.width, image.height, image.config)
    val kernelSize = 11 // Adjust for desired blur strength (odd numbers recommended)

    for (x in 0 until image.width) {
        for (y in 0 until image.height) {
            var newRed = 0
            var newGreen = 0
            var newBlue = 0
            var count = 0

            for (i in -kernelSize / 2..kernelSize / 2) {
                for (j in -kernelSize / 2..kernelSize / 2) {
                    val neighborX = x + i
                    val neighborY = y + j

                    if (neighborX >= 0 && neighborX < image.width && neighborY >= 0 && neighborY < image.height) {
                        val pixel = image.getPixel(neighborX, neighborY)
                        newRed += Color.red(pixel)
                        newGreen += Color.green(pixel)
                        newBlue += Color.blue(pixel)
                        count++
                    }
                }
            }

            newRed /= count
            newGreen /= count
            newBlue /= count

            val newPixel = Color.argb(Color.alpha(image.getPixel(x, y)), newRed, newGreen, newBlue)
            newBitmap.setPixel(x, y, newPixel)
        }
    }

    Image(bitmap = newBitmap.asImageBitmap(), contentDescription = "Description")
}


