package com.example.smartnote.helpers

import android.content.Context
import android.graphics.Bitmap
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FileSystemHelper(@ApplicationContext var context: Context) {

  suspend fun makeFolder(folderName: String, filePath: String) {
    val medFolder = File(context.filesDir.toString() + filePath, folderName)
    if (!medFolder.exists()) {
      medFolder.mkdirs()
    }
  }
  suspend fun storeImage(bitmap: Bitmap, fileName: String, filePath: String) {

    val directory = File(context.filesDir.toString() + filePath, "unit" + fileName)
    if (!directory.exists()) {
      directory.mkdir()
    }
    val timeStamp =
      SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val mypath = File(directory, "IMG_" + timeStamp + ".jpeg")

    var fos: FileOutputStream? = null
    try {
      fos = FileOutputStream(mypath)
      bitmap.compress(Bitmap.CompressFormat.JPEG, 70, fos)
      fos.close()
    } catch (e: Exception) {
    }
  }

  suspend fun getFilesList(folderPath: String): Array<File>? {
    val path = File(context.filesDir.toString() + folderPath)
    return path.listFiles()
  }

  suspend fun getFirstImage(folderPath: String): File? {
    val path = File(context.filesDir.toString() + folderPath)
    val list = path.listFiles()
    if (list != null && list.isNotEmpty()) {
      for (currentFile in list) {
        if (currentFile.name.endsWith(".jpeg")) {
          return currentFile
        }
      }
    }
    return list[0]
  }

  suspend fun deleteFile(fileName: String) {
    val file = File(fileName)
    if (file.exists()) {
      file.delete()
    }
  }
}
