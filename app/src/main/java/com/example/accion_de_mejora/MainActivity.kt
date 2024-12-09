package com.example.accion_de_mejora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore



class MainActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var valor1Text: TextView
    private lateinit var valor2Text: TextView
    private lateinit var valor3Text: TextView
    private lateinit var resultText: TextView
    private lateinit var sumarButton: Button
    private lateinit var restarButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        db = FirebaseFirestore.getInstance()


        valor1Text = findViewById(R.id.textView1)
        valor2Text = findViewById(R.id.textView2)
        valor3Text = findViewById(R.id.textView3)
        resultText = findViewById(R.id.resultTextView)
        sumarButton = findViewById(R.id.sumarButton)
        restarButton = findViewById(R.id.restarButton)


        val docRef = db.collection("valores").document("doc_id1")
        docRef.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {

                val valor1 = documentSnapshot.getString("valor1")?.toIntOrNull() ?: 0
                val valor2 = documentSnapshot.getString("valor2")?.toIntOrNull() ?: 0
                val valor3 = documentSnapshot.getString("valor3")?.toIntOrNull() ?: 0


                valor1Text.text = "Valor 1: $valor1"
                valor2Text.text = "Valor 2: $valor2"
                valor3Text.text = "Valor 3: $valor3"
            } else {
                resultText.text = "Documento no encontrado"
            }
        }.addOnFailureListener { exception ->
            resultText.text = "Error al obtener los datos: ${exception.message}"
        }

        sumarButton.setOnClickListener {
            val v1 = valor1Text.text.split(": ")[1].toIntOrNull() ?: 0
            val v2 = valor2Text.text.split(": ")[1].toIntOrNull() ?: 0
            val v3 = valor3Text.text.split(": ")[1].toIntOrNull() ?: 0


            val suma = v1 + v2 + v3
            resultText.text = "Resultado: $suma"
        }


        restarButton.setOnClickListener {
            val v1 = valor1Text.text.split(": ")[1].toIntOrNull() ?: 0
            val v2 = valor2Text.text.split(": ")[1].toIntOrNull() ?: 0
            val v3 = valor3Text.text.split(": ")[1].toIntOrNull() ?: 0


            val resta = v1 - v2 - v3
            resultText.text = "Resultado: $resta"
        }
    }
}
