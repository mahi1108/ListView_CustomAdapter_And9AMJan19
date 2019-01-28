package cubex.mahesh.listview_and9amjan19

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        readFiles()
    }  // onCreate

    fun readFiles( ){
        var path = "/storage/sdcard0/"
        var f = File(path)
        if(!f.exists()){
            path = "/storage/emulated/0/"
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
