fun test() {
  val context = LocalContext.current
  val image = BitmapFactory.decodeResource(context.resources, R.drawable.your_image_id)

  val newBitmap = Bitmap.createBitmap(image.width, image.height, image.config)
  val kernelSize = 3 // Adjust for desired blur strength (odd numbers recommended)

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
