package com.placebox.ktx.content

import android.app.AlarmManager
import android.app.DownloadManager
import android.app.NotificationManager
import android.app.SearchManager
import android.app.job.JobScheduler
import android.appwidget.AppWidgetManager
import android.content.ClipboardManager
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.view.inputmethod.InputMethodManager
import androidx.preference.PreferenceManager

val Context.alarmManager get() = getSystemService(Context.ALARM_SERVICE) as AlarmManager
val Context.notificationManager get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
val Context.jobScheduler get() = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
val Context.downloadManager get() = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
val Context.clipboardManager get() = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
val Context.searchManager get() = getSystemService(Context.SEARCH_SERVICE) as SearchManager
val Context.inputManager get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
val Context.connectivityManager get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

val Context.appWidgetManager: AppWidgetManager get() = AppWidgetManager.getInstance(this)
val Context.preferences: SharedPreferences get() = PreferenceManager.getDefaultSharedPreferences(this)


