import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.Single


object InternetConnectionUtil {
    fun isInternetOn(context: Context): Single<Boolean> {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return Single.just(activeNetworkInfo != null && activeNetworkInfo.isConnected)
    }
}