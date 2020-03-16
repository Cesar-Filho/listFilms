package com.example.listfilms

import android.os.AsyncTask
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.listfilms.model.User
import com.example.listfilms.persistence.MainDataBase.getInstance
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        email.setText(intent?.getStringExtra("EMAIL"))
        signUp.setOnClickListener(this)
    }

    private fun startSignUp(email: String, password: String) {
        if (!validateForm()) {
            return
        }
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val insert = Runnable {
                        val appDatabase =
                            getInstance(applicationContext)
                        val user = User(0, email)
                        appDatabase?.userDao()?.save(user)
                    }
                    AsyncTask.execute(insert);
                    Toast.makeText(
                        baseContext, "Create user success.",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                } else {
                    Toast.makeText(
                        baseContext, "Create user failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
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
        startSignUp(email.text.toString(), password.text.toString())
    }
}
