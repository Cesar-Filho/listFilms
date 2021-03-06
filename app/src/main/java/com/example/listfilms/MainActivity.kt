package com.example.listfilms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val email = findViewById<TextView>(R.id.email)
        val password = findViewById<TextView>(R.id.password)
        email.text = "cesar.pc.filho@gmail.com"
        password.text = "123456"
        signIn.setOnClickListener(this)
        recovery.setOnClickListener(this)
        signUp.setOnClickListener(this)
    }

    private fun startSignIn(email: String, password: String) {
        if (!validateForm()) {
            return
        }
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        baseContext, "Authentication success.",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this, ListActivity::class.java).apply {
                        putExtra("EMAIL", email)
                    }
                    UserSingleton.setUser(email)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun startSignUp() {
        val intent: Intent = Intent(this, RegisterActivity::class.java).apply {
            putExtra("EMAIL", email.text.toString())
        }
        startActivity(intent)
    }

    private fun recovery() {
        val intent: Intent = Intent(this, RecoveryActivity::class.java).apply {
            putExtra("EMAIL", email.text.toString())
        }
        startActivity(intent)
    }

    private fun validateForm(): Boolean {
        var valid = true

        val fieldEmail = email.text.toString()
        if (TextUtils.isEmpty(fieldEmail)) {
            email.error = "Required."
            valid = false
        } else {
            email.error = null
        }

        val fieldPassword = password.text.toString()
        if (TextUtils.isEmpty(fieldPassword)) {
            password.error = "Required."
            valid = false
        } else {
            password.error = null
        }

        return valid
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.signIn -> startSignIn(email.text.toString(), password.text.toString())
            R.id.signUp -> startSignUp()
            R.id.recovery -> recovery()
        }
    }

}
