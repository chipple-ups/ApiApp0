package jp.techacademy.hiroshi.okazawa.apiapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import io.realm.annotations.PrimaryKey
import kotlinx.android.synthetic.main.activity_web_view.*
import java.io.Serializable


class WebViewActivity: AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())
    //private var fragmentCallback : FragmentCallback? = null // Fragment -> Activity にFavoriteの変更を通知する
    private val viewPagerAdapter by lazy { ViewPagerAdapter(this) }

    @PrimaryKey
    var id: String = ""
    var imageUrl: String = ""
    var name: String = ""
    var url: String = ""



    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        //webView.loadUrl(intent.getStringExtra(KEY_URL).toString())


        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener() {

            Log.d("UI_PARTS", "ボタンをタップしました")
            FavoriteShop.insert(FavoriteShop().apply {
                val resultIntent = Intent(applicationContext, MainActivity::class.java)
                resultIntent.putExtra(Intent.EXTRA_TEXT, favoriteShop)

                //id = SHOPID.id
                //name = SHOPID.name
                //imageUrl = SHOPID.logoImage
                //url = if (SHOPID.couponUrls.sp.isNotEmpty()) SHOPID.couponUrls.sp else SHOPID.couponUrls.pc
                //})
            })
        }

    }

    companion object{
        private const val VIEW_PAGER_POSITION_API = 0
        private const val VIEW_PAGER_POSITION_FAVORITE = 1
        private const val SHOPID = "shopid"
        fun start(activity: Activity, favoriteShop: Shop) {
            activity.startActivity(Intent(activity, WebViewActivity::class.java).putExtra(SHOPID, favoriteShop))
        }
       //private const val KEY_URL = "key_url"
        //fun start(activity: Activity, url: String) {
            //activity.startActivity(Intent(activity, WebViewActivity::class.java).putExtra(KEY_URL, url))
        //}


    }
}