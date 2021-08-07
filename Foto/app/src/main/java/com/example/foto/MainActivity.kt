package com.example.foto

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts


class MainActivity : AppCompatActivity() {
    private lateinit var btnCamera : Button
    private lateinit var imageCamera : ImageView
    private lateinit var activityResultLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btnCamera = findViewById(R.id.btnPhoto)
        this.imageCamera = findViewById(R.id.imageView)

        this.btnCamera.setOnClickListener( {camera()} )

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result : ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                val bundle : Bundle = result.data!!.extras!!
                val bitmap : Bitmap = bundle.get("data") as Bitmap
                this.imageCamera.setImageBitmap(bitmap)
            }
        }
    }

    fun camera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(packageManager) != null) {
            activityResultLauncher.launch(intent);
        }
    }
}

