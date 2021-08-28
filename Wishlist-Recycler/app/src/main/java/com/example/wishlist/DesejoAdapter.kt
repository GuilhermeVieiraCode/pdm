package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DesejoAdapter(val lista: MutableList<Desejo>): RecyclerView.Adapter<DesejoAdapter.DesejoViewHolder>() {
    var listener: OnItemClickRecycleView? = null
    var secListener: OnItemLongClickRecycleView? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesejoViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_item, parent, false)
        return DesejoViewHolder(view)
    }

    override fun onBindViewHolder(holder: DesejoAdapter.DesejoViewHolder, position: Int) {
        val desejo = this.lista.get(position)
        holder.tvDescricao.text = desejo.descricao
        holder.tvNota.text = desejo.nota.toString()
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }

    inner class DesejoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvDescricao: TextView
        var tvNota: TextView

        init {
            this.tvDescricao = itemView.findViewById(R.id.tvItemDescricao)
            this.tvNota = itemView.findViewById(R.id.tvItemNota)

            itemView.setOnClickListener{
                this@DesejoAdapter.listener?.onItemClick(this.adapterPosition)
            }

            itemView.setOnLongClickListener{
                this@DesejoAdapter.secListener?.onItemLongClick(this.adapterPosition)
                return@setOnLongClickListener true
            }
        }

    }


}