package com.islam.task.generalUtils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.islam.task.BuildConfig
import com.islam.task.R
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import java.io.ByteArrayOutputStream

object Utils {

    const val DEVURL = "https://api-aws-eu-qa-1.auto1-test.com/v1/car-types/"
    const val URL = "https://api-aws-eu-qa-1.auto1-test.com/v1/car-types/"

    fun getUrl(): String {
        return if (BuildConfig.DEBUG)
            DEVURL
        else
            URL
    }

    fun hideKeyboard(context: Activity) {
        val view = context.currentFocus
        view?.let { v ->
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    fun Context.toast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG ).show()
    }

    fun View.snackbar(message: String){
        Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE).also { snackbar ->
            snackbar.setAction(context.getString(R.string.ok)) {
                snackbar.dismiss()
            }
            snackbar.setActionTextColor(Color.WHITE)
        }.show()
    }


    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun permissionCheck(
        activity: Activity?,
        permission: String?
    ): Boolean {
        return ActivityCompat.checkSelfPermission(
            activity!!,
            permission!!
        ) == PackageManager.PERMISSION_GRANTED
    }


    fun convertToStringBase64(context: Context, selectedfile: Uri): String {
        var bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, selectedfile)

        var bitmapOut = Bitmap.createScaledBitmap(bitmap, 320, 480, false)

        var outputStream = ByteArrayOutputStream()
        bitmapOut.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        var byteArray = outputStream.toByteArray()

        //Use your Base64 String as you wish
        var encodedString = Base64.encodeToString(byteArray, Base64.DEFAULT)

        var encodedWithdata = "data:image/png;base64,$encodedString"

        Timber.d("encodedImage  $encodedWithdata")

        return encodedWithdata
    }

    fun openUrl(context: Context, url: String?) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        context.startActivity(intent)
    }

}