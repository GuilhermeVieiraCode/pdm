package com.example.wishlist

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private lateinit var rvDesejos: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var lista: MutableList<Desejo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.lista = mutableListOf()

        this.rvDesejos = findViewById(R.id.rvDesejos)
        this.fabAdd = findViewById(R.id.fabAdd)

        this.rvDesejos.adapter = DesejoAdapter(this.lista)
        (this.rvDesejos.adapter as DesejoAdapter).listener = OnItemClickListener()
        (this.rvDesejos.adapter as DesejoAdapter).secListener = OnItemLongClickListener()

        val resultForm = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                val desejo = it.data?.getSerializableExtra("DESEJO") as Desejo
                this.lista.add(desejo)
                (this.rvDesejos.adapter as DesejoAdapter).notifyDataSetChanged()
            }
        }

        this.fabAdd.setOnClickListener{
            val intent = Intent(this, FormActivity::class.java)
            resultForm.launch(intent)
        }


    }

    inner class OnItemClickListener: OnItemClickRecycleView{
        override fun onItemClick(position: Int) {
            val desejo = this@MainActivity.lista.get(position)
            Toast.makeText(this@MainActivity, desejo.descricao, Toast.LENGTH_SHORT).show()
        }
    }

    inner class OnItemLongClickListener: OnItemLongClickRecycleView{
        override fun onItemLongClick(position: Int) {
            val alert = AlertDialog.Builder(
                this@MainActivity
            )
            alert.setTitle("Atenção")
            alert.setMessage("Excluir item?")
            alert.setPositiveButton(R.string.sim) { dialog, id ->
                this@MainActivity.lista.removeAt(position)
                (this@MainActivity.rvDesejos.adapter as DesejoAdapter).notifyDataSetChanged()
                dialog.dismiss()
            }
            alert.setNegativeButton(R.string.nao) { dialog, id ->
                dialog.dismiss()
            }
            alert.show()
        }
    }
}

/*
        this.lvDesejos.setOnItemClickListener(OnItemClick())
        //this.lvDesejos.setOnItemLongClickListener(OnItemLongClick())
        this.lvDesejos.onItemLongClickListener = OnItemLongClickListener { parent, v, position, id -> //Do your tasks here
            val alert = AlertDialog.Builder(
                this@MainActivity
            )
            alert.setTitle("Atenção")
            alert.setMessage("Excluir item?")
            alert.setPositiveButton(R.string.sim) { dialog, id ->
                this@MainActivity.lista.removeAt(position)
                (this@MainActivity.lvDesejos.adapter as ArrayAdapter<Desejo>).notifyDataSetChanged()
                dialog.dismiss()
            }
            alert.setNegativeButton(R.string.nao) { dialog, id ->
                dialog.dismiss()
            }
            alert.show()
            true
        }
    }

    inner class OnItemClick: AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val desejo = this@MainActivity.lista[position]
            Toast.makeText(this@MainActivity, desejo.descricao, Toast.LENGTH_SHORT).show()
        }

         */

