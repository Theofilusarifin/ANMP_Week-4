package id.ac.ubaya.advweek4.view

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import id.ac.ubaya.advweek4.R
import id.ac.ubaya.advweek4.util.createNotificationChannel

class MainActivity : AppCompatActivity() {
    init{
//        Akan dieksekusi ketika program dibuat pertama kali
//        Contoh : memastikan integer ga boleh negatif, dan lain lain
        instance = this
    }

//    Di kotlin ga ada static adanya companion object
    companion object {
        private var instance: MainActivity? = null
        fun showNotification(title: String, content: String, icon: Int) {
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"
        }
    }

    fun showNotification(title:String, content:String, icon:Int) {
        val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"

//        .apply used for method chaining, kalau ga pake bisa aja tinggal kasik titik di tiap method yang akan dipanggil
        val notificationBuilder = NotificationCompat.Builder(instance!!.applicationContext, channelId).apply {
            setSmallIcon(icon)
            setContentTitle(title)
            setContentText(content)
            setStyle(NotificationCompat.BigTextStyle())
            priority = NotificationCompat.PRIORITY_DEFAULT
//            Set auto cancel supaya notifikasi bisa ditutup ketika bagian di luar notifikasi di klik
            setAutoCancel(true)
        }

        val notificationManager = NotificationManagerCompat.from(instance!!.applicationContext.applicationContext!!)
        if (ActivityCompat.checkSelfPermission(
                instance!!.applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ){
            return
        }
        notificationManager.notify(1001, notificationBuilder.build())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, false, getString(R.string.app_name), "App notification channel.")
    }
}