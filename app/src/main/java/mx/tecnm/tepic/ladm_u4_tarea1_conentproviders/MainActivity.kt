package mx.tecnm.tepic.ladm_u4_tarea1_conentproviders

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.ContactsContract
import android.util.Log
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var siFechas = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            Fechas()
        }


    }

    private fun Fechas() {
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_CALENDAR),siFechas)
        }else{
            var cal = ArrayList<String>()
            var proyecta = arrayOf(CalendarContract.Calendars.NAME)

            try {
                var Ccalendario = contentResolver.query(CalendarContract.Calendars.CONTENT_URI,proyecta,null,null,null)
                if (Ccalendario!!.moveToFirst()){
                    do{
                        cal.add((Ccalendario.getString(0)))
                    }while (Ccalendario.moveToNext())
                }else{
                    cal.add("NO HAY FECHAS")
                }
                lista.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,cal)
                Ccalendario.close()
            }catch (e:Exception){
                Toast.makeText(this,"ERROR EN CALENDARIO"+e.message,Toast.LENGTH_LONG).show()
            }

        }

    }
}