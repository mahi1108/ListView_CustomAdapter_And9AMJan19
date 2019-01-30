package cubex.mahesh.listview_and9amjan19

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
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
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
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
        var files : Array<String>  = f.list()
        var myadapter = ArrayAdapter<String>(
            this@MainActivity,
                android.R.layout.simple_list_item_single_choice,
                    files)
         lview.adapter =   myadapter
    }


} // MainActivity
