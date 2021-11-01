package com.xrafff.belajar_crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ref = FirebaseDatabase.getInstance().getReference("USERS")

        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            savedata()
            val intent = Intent (this, show::class.java)
            startActivity(intent)
        }
    }

    private fun savedata() {
        val inputNama = findViewById<EditText>(R.id.inputNama)
        val inputStatus = findViewById<EditText>(R.id.inputStatus)

        val nama = inputNama.text.toString()
        val status = inputStatus.text.toString()

        val user = User(nama,status)
        val userId = ref.push().key.toString()

        ref.child(userId).setValue(user).addOnCompleteListener{
            Toast.makeText(this, "Success",Toast.LENGTH_SHORT).show()
            inputNama.setText("")
            inputStatus.setText("")
        }
    }
}