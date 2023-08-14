package com.relevanx.tcom.activity.tournament

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.relevanx.tcom.api.ApiConfig
import com.relevanx.tcom.api.MakeOrderRequest
import com.relevanx.tcom.api.TeamRequest
import com.relevanx.tcom.databinding.ActivityRegiterTournamentBinding
import com.relevanx.tcom.utils.SavedPreference
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegiterTournamentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegiterTournamentBinding
    private lateinit var bitmap: Bitmap
    lateinit var imageUrl: String
    private var selectedImageUri: Uri? = null
    private val storageRef = Firebase.storage.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegiterTournamentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogo.setOnClickListener{
            getContent.launch(Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "image/*"
            })

        }

        binding.submitButton.setOnClickListener {
            val nameTim = binding.edRegisterName.text.toString()
            val phone = binding.edRegisterContact.text.toString()
            val address = binding.edRegisterAlamat.text.toString()

            if (nameTim.isEmpty() || phone.isEmpty() || address.isEmpty() || selectedImageUri == null) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                selectedImageUri?.let { uri ->
                    val fileName = getFileName(applicationContext, uri)
                    val uploadTask = storageRef.child("file/$fileName").putFile(uri)
                    uploadTask.addOnSuccessListener { uploadTaskSnapshot ->
                        uploadTaskSnapshot.storage.downloadUrl.addOnSuccessListener { downloadUri ->
                            imageUrl = downloadUri.toString()
                            Log.e("Firebase", "Image URL: $imageUrl")
                            uploadTeam()

                            val uid = SavedPreference.getUid(this)
                            val tournamentID = intent.getIntExtra(EXTRA_TOUR, 0)
                            val teamRequest = TeamRequest(
                                idTournamen = tournamentID,
                                name = nameTim,
                                idUser = uid,
                                noTelp = phone,
                                gambar = imageUrl,
                                alamat = address
                            )

                            val apiService = ApiConfig.getApiService()
                            val call = apiService.postPartisipan(teamRequest)
                            call.enqueue(object : Callback<ResponseBody> {
                                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                                    if (response.isSuccessful) {

                                        val callSecon = apiService.postMakeOrder(MakeOrderRequest(uid, tournamentID))
                                        callSecon.enqueue(object : Callback<ResponseBody> {
                                            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                                                if (response.isSuccessful) {
                                                    Toast.makeText(this@RegiterTournamentActivity, "Berhasil", Toast.LENGTH_SHORT).show()
                                                    val intent = Intent(this@RegiterTournamentActivity, DetailRegisterTournament::class.java)
                                                    intent.putExtra(DetailRegisterTournament.EXTRA_NAME, teamRequest)
                                                    startActivity(intent)
                                                } else {
                                                    Toast.makeText(this@RegiterTournamentActivity, "Gagal", Toast.LENGTH_SHORT).show()
                                                }
                                            }

                                            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                                Toast.makeText(this@RegiterTournamentActivity, "Failed", Toast.LENGTH_SHORT).show()
                                            }
                                        })

                                    }
                                }

                                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                    Toast.makeText(this@RegiterTournamentActivity, "Failed", Toast.LENGTH_SHORT).show()
                                }
                            })


//                            Intent(this, DetailAfterRegisterActivity::class.java).also {
//                                it.putExtra(DetailAfterRegisterActivity.EXTRA_IMAGE, imageUrl)
//                                startActivity(it)
//                            }
                        }.addOnFailureListener {
                            Log.e("Firebase", "Failed to get image URL")
                            Toast.makeText(this, "Upload Failed URL", Toast.LENGTH_SHORT).show()
                        }
                    }.addOnFailureListener {
                        Log.e("Firebase", "Image Upload failed")
                        Toast.makeText(this, "Upload Failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.back.setOnClickListener {
            finish()
        }
    }

    fun uploadTeam(){

    }



    @SuppressLint("Range")
    private fun getFileName(context: Context, uri: Uri): String? {
        if (uri.scheme == "content") {
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            cursor.use {
                if (cursor != null) {
                    if(cursor.moveToFirst()) {
                        return cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                    }
                }
            }
        }
        return uri.path?.lastIndexOf('/')?.let { uri.path?.substring(it) }
    }

    private val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, data?.data)
            if (data != null) {
                selectedImageUri = data.data
                binding.imageTournament.setImageURI(selectedImageUri)
            }
        }
    }


    companion object {
        const val EXTRA_TOUR = "extra_tour"
    }

}