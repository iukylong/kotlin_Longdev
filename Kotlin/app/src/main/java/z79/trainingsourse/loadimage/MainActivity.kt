package z79.trainingsourse.loadimage

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val url: String = "https://i-xem.mkocdn.com/i.xem.sb/data/photo/2016/06/11/011/medium-1465625011_8C05ED909FE98055EF68DAC96ADDD33F-650.jpg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_Download.setOnClickListener() {
            loadImage().execute(url)
        }
    }

    inner class loadImage : AsyncTask<String, Void, Bitmap>() {
        val mProgress = ProgressDialog(this@MainActivity)

        override fun onPreExecute() {
            super.onPreExecute()
            mProgress.setMessage("Loading...")
            mProgress.show()
        }

        override fun doInBackground(vararg params: String?): Bitmap {
            var url = URL(params[0])
            var input = url.openConnection().getInputStream()
            var bitmap: Bitmap = BitmapFactory.decodeStream(input)

            return bitmap
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            mProgress.dismiss()
            img_Download.setImageBitmap(result)
        }
    }
}
