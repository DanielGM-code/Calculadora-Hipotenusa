package com.example.calculadorahipotenusa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.Button

import android.widget.TextView

import android.widget.EditText
import android.widget.Toast
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cateto_a = findViewById<View>(R.id.editTextCatetoA) as EditText
        cateto_b = findViewById<View>(R.id.editTextCatetoB) as EditText
        calcular = findViewById<View>(R.id.buttonCalcular) as Button
        limpiar = findViewById<View>(R.id.buttonLimpiar) as Button

        cateto_a!!.setText("")
        cateto_b!!.setText("")
        limpiar!!.setEnabled(false)

        calcular!!.setOnClickListener(View.OnClickListener {
            if (validar_campos()) {
                val cateto_a: String = cateto_a!!.getText().toString()
                val cateto_b: String = cateto_b!!.getText().toString()

                val a: Double = cateto_a!!.toDouble()
                val b: Double = cateto_b!!.toDouble()
                val r = Math.pow(Math.pow(a, 2.0) + Math.pow(b, 2.0), 0.5)

                respuesta = java.lang.String.valueOf(formato.format(r))
                if (r % 1.0 == 0.0) {
                    respuesta = r as String
                }

                var mensaje = "La hipotenusa es:  $respuesta"
                mostrar_mensaje(mensaje)

                limpiar_controles()

                limpiar!!.isEnabled = true
                calcular!!.isEnabled = false
            }else{
                var mensaje = "Favor de llenar todos los campos"
                mostrar_mensaje(mensaje)
            }
        })

        limpiar!!.setOnClickListener(View.OnClickListener {
            limpiar_controles()
        })
    }
    var calcular: Button? = null
    var limpiar: Button? = null
    var cateto_a: EditText? = null
    var cateto_b: EditText? = null
    var formato: DecimalFormat = DecimalFormat("#.00")
    var respuesta: String? = null

    fun limpiar_controles() {
        cateto_b!!.setText("")
        cateto_a!!.setText("")
        cateto_a!!.requestFocus()
        limpiar!!.setEnabled(false)
        calcular!!.setEnabled(true)
    }

    fun validar_campos(): Boolean{
        return !cateto_a?.text.toString().equals("") ||
                !cateto_b?.text.toString().equals("")
    }

    fun mostrar_mensaje(mensaje :String){
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}