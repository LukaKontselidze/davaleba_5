package com.example.davaleba_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var editTextEmail2: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editPassword2: EditText
    private lateinit var buttonRegister: Button

    override fun onCreate(savedInstate: Bundle?) {
        super.onCreate(savedInstate)
        setContentView(R.layout.activity_login)

        init()
        registerlistener()

    }

    private fun init() {
        editTextEmail2 = findViewById(R.id.editTextEmail2)
        editTextPassword = findViewById(R.id.editTextPassword)
        editPassword2 = findViewById(R.id.editPassword2)
        buttonRegister = findViewById(R.id.buttonRegister)


    }

    private fun registerlistener() {
        buttonRegister.setOnClickListener() {
            val email = editTextEmail2.text.toString()
            val pass = editTextPassword.text.toString()
            val pass2 = editPassword2.text.toString()

            if (email.isEmpty() || pass.isEmpty() || pass2.isEmpty()) {
                Toast.makeText(this, "გთხოვთ შეიყვანოთ ინფორმაცია", Toast.LENGTH_LONG).show()
                return@setOnClickListener

            } else if (pass.length < 9) {
                editTextPassword.error = "Password should contain more than 8 symbols and digits"
                return@setOnClickListener


            } else if (!pass.contains(Regex("^(?=.*[0-9])(?=.[a-z])(?=.*[A-Z])(?=.*[@!#$%&*/?])"))) {
                editTextPassword.error =
                    "Password should contain symbols, digits, lowercase and uppercase letters"
                return@setOnClickListener

            } else if (pass != pass2) {
                editTextPassword.error = "Passwords are not similar, please try again"
                return@setOnClickListener
            }
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        gotoSuccess()
                    } else {
                        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()

                    }

                }

        }

    }

    private fun gotoSuccess() {
        val intent = Intent(this, Success::class.java)
        startActivity(intent)
        finish()
    }


    }





