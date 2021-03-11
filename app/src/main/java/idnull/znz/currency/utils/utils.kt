package idnull.znz.currency.utils


import android.widget.Toast
import idnull.znz.currency.MainActivity
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

lateinit var APP:MainActivity

fun showToast(massage:String){
    Toast.makeText(APP,massage,Toast.LENGTH_LONG).show()
}


fun convertTimestampToTime(timestamp: Long?): String {
    if (timestamp == null) return ""
    val stamp = Timestamp(timestamp * 1000)
    val date = Date(stamp.time)
    val pattern = "HH:mm:ss"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)
}