package com.example.listfilms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_recovery.*

class RecoveryActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recovery)

        recovery_email.setText(intent?.getStringExtra("EMAIL"))
        recovery.setOnClickListener(this)
    }

    private fun recovery(email: String) {
        if (!validateForm()) {
            return
        }
        auth = FirebaseAuth.getInstance()
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        baseContext, "Recovery success.",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                } else {
                    Toast.makeText(
                        baseContext, "Recovery  failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun validateForm(): Boolean {
        var valid = true

        val fieldEmail = recovery_email.text.toString()
        if (TextUtils.isEmpty(fieldEmail)) {
            recovery_email.error = "Required."
            valid = false
        } else {
            recovery_email.error = null
        }

        return valid
    }

    override fun onClick(v: View?) {
        recovery(recovery_email.text.toString())
    }
}
