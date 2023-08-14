package com.relevanx.tcom.activity.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.relevanx.tcom.databinding.ActivityRegistrasiBinding

class RegistrasiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrasiBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrasiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.signupButton.setOnClickListener {
            val name = binding.edRegisterName.text.toString()
            val email = binding.edRegisterEmail.text.toString()
            val date = binding.edRegisterBirth.text.toString()
            val phone = binding.edRegisterPhone.text.toString()
            val city = binding.edRegisterKota.text.toString()
            val password = binding.edRegisterPassword.text.toString()
            val confirmPassword = binding.confirmedRegisterPassword.text.toString()

            if (name.isEmpty() || email.isEmpty() || date.isEmpty() ||phone.isEmpty() ||city.isEmpty() ||
                password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Data must be filled", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Password and confirm password must match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            val profileUpdates = UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .build()

                            user?.updateProfile(profileUpdates)?.addOnCompleteListener { profileTask ->
                                if (profileTask.isSuccessful) {

//                                    val db = FirebaseFirestore.getInstance()
//                                    val userData = hashMapOf(
//                                        "displayName" to "John Doe",
//                                        "profilePictureUrl" to "https://example.com/profile.jpg"
//                                        // Add more data as needed
//                                    )
//                                    db.collection("users").document(user.uid).set(userData)
//                                        .addOnSuccessListener {
//                                            // User data saved successfully
//                                            Toast.makeText(this, "Register Success User: $user", Toast.LENGTH_SHORT).show()
//                                        }
//                                        .addOnFailureListener { exception ->
//                                            // Failed to save user data
//                                            Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()
//                                        }
                                    finish()
                                } else {
                                    Toast.makeText(this, "Failed to update profile", Toast.LENGTH_SHORT).show()
                                }
                            }
                        } else {
                            Toast.makeText(this, "Register Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}