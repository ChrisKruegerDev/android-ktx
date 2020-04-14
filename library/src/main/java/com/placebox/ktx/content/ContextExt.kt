package com.placebox.ktx.content

import android.app.*
import android.app.job.JobScheduler
import android.appwidget.AppWidgetManager
import android.content.ClipboardManager
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.IBinder
import android.view.inputmethod.InputMethodManager
import androidx.annotation.*
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import java.util.NoSuchElementException

val Context.alarmManager get() = getSystemService(Context.ALARM_SERVICE) as AlarmManager
val Context.notificationManager get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
val Context.jobScheduler get() = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
val Context.downloadManager get() = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
val Context.clipboardManager get() = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
val Context.searchManager get() = getSystemService(Context.SEARCH_SERVICE) as SearchManager
val Context.inputManager get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
val Context.connectivityManager get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
val Context.activityManager get() = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

val Context.appWidgetManager: AppWidgetManager get() = AppWidgetManager.getInstance(this)
val Context.preferences: SharedPreferences get() = PreferenceManager.getDefaultSharedPreferences(this)

val Context.isOnline: Boolean
    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    get() {
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
    }


fun Context.dimensionPixelSize(@DimenRes id: Int) = resources.getDimensionPixelSize(id)
fun Context.dimension(@DimenRes id: Int) = resources.getDimension(id)
fun Context.integer(@IntegerRes id: Int) = resources.getInteger(id)
fun Context.drawable(id: Int) = resources.getDrawable(id, theme)
fun Context.color(@ColorRes id: Int) = ContextCompat.getColor(this, id)

@ColorInt
fun Context.colorAttr(attr: Int): Int {
    if (attr == 0) throw NoSuchElementException("attr == 0")
    val typedArray = theme.obtainStyledAttributes(intArrayOf(attr))
    val color = typedArray.getColor(0, Color.DKGRAY)
    typedArray.recycle()
    return color
}

fun Context.dimensionAttr(@AttrRes attr: Int): Float {
    val a = theme.obtainStyledAttributes(intArrayOf(attr))
    return try {
        a.getDimension(0, 0f)
    } finally {
        a.recycle()
    }
}


val Context.isLowMemory: Boolean
    get() {
        val memoryInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memoryInfo)
        return memoryInfo.lowMemory
    }

val Context.firstInstallTime: Long?
    get() = try {
        packageManager.getPackageInfo(packageName, 0).firstInstallTime
    } catch (e: PackageManager.NameNotFoundException) {
        null
    }

val Context.lastUpdateTime: Long?
    get() = try {
        packageManager.getPackageInfo(packageName, 0).lastUpdateTime
    } catch (e: PackageManager.NameNotFoundException) {
        null
    }

val Context.isInstallFromUpdate: Boolean
    get() = firstInstallTime != lastUpdateTime

fun Context.hideKeyboard(windowToken: IBinder) = inputManager.hideSoftInputFromWindow(windowToken, 0)
