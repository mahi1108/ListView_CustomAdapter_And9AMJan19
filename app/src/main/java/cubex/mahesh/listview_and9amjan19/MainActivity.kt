package cubex.mahesh.listview_and9amjan19

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.indiview.*
import kotlinx.android.synthetic.main.indiview.view.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var status = ContextCompat.checkSelfPermission(
            this@MainActivity,
            Manifest.permission.READ_EXTERNAL_STORAGE)
        if(status == PackageManager.PERMISSION_GRANTED) {
            readFiles()
        }else{
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                123)
        }
    }  // onCreate


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            readFiles()
        }else{
            Toast.makeText(this@MainActivity,
                "You can't read the with out storage permission ",
                Toast.LENGTH_LONG).show()
        }

    }



    fun readFiles( ){
        var path = "/storage/sdcard0/GBWhatsApp/Media/GBWhatsApp Images/Sent/"
        var f = File(path)
        if(!f.exists()){
            path = "/storage/emulated/0/GBWhatsApp/Media/GBWhatsApp Images/Sent/"
            f = File(path)
        }
        var files : Array<File>  = f.listFiles()
        var myadapter = object : BaseAdapter() {
            override fun getCount(): Int = files.size

            override fun getItem(p0: Int): Any = 0

            override fun getItemId(p0: Int): Long = 0

            override fun getView(pos: Int, p1: View?, p2: ViewGroup?): View {

            var inflater = LayoutInflater.from(this@MainActivity)
            var v = inflater.inflate(R.layout.indiview,null)

               var file =  files[pos]
              //  var u = Uri.fromFile(file)
             //   v.iview.setImageURI(u)
                       // Thumbnail creation
                var bmp = BitmapFactory.decodeFile(file.path)
                var compressed_bmp = ThumbnailUtils.
                    extractThumbnail(bmp, 100,80)
                v.iview.setImageBitmap(compressed_bmp)

                v.name.text = file.name
                v.size.text = file.length().toString()

                v.del.setOnClickListener {
                    file.delete()
                    readFiles()
                }

            return  v
            }
        }
         lview.adapter =   myadapter
    }


} // MainActivity
