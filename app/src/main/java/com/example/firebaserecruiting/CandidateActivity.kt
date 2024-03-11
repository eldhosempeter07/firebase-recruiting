package com.example.firebaserecruiting

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaserecruiting.MainActivity
import com.example.firebaserecruiting.R
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase

class CandidateActivity: AppCompatActivity()  {
    private var adapter:CandidateAdapter? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidate)
        val btn2: ImageButton = findViewById(R.id.imageButton2)
        btn2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent) }

        val btn4: ImageButton = findViewById(R.id.imageButton4)
        btn4.setOnClickListener {
            val intent = Intent(this, CandidateActivity::class.java)
            startActivity(intent)}

        val query = FirebaseDatabase.getInstance().reference.child("recruitment")
        val options = FirebaseRecyclerOptions.Builder<Candidate>().setQuery(query,Candidate::class.java).build()
        adapter = CandidateAdapter(options)
        val rview: RecyclerView = findViewById(R.id.rView)
        rview.layoutManager = GridLayoutManager(this,2)
        rview.adapter = adapter

        val home: ImageButton = findViewById(R.id.imageButton1)
        home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val back: ImageView = findViewById(R.id.arrowback)
        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart(){
        super.onStart()
        adapter?.startListening()
    }
}