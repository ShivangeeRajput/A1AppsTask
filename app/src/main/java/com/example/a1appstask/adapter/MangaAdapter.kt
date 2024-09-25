package com.example.a1appstask.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a1appstask.R
import com.example.a1appstask.databinding.ItemMangaBinding
import com.example.a1appstask.models.Manga

class MangaAdapter(
    private var mangaList: List<Manga>
) : RecyclerView.Adapter<MangaAdapter.MangaViewHolder>() {

    inner class MangaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.mangaTitle)
        val imageView: ImageView = view.findViewById(R.id.mangaImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_manga, parent, false)
        return MangaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        val manga = mangaList[position]
        holder.titleTextView.text = manga.title


        Glide.with(holder.itemView.context)
            .load(manga.imageUrl)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = mangaList.size


    fun updateData(newMangaList: List<Manga>) {
        mangaList = newMangaList
        notifyDataSetChanged()
    }
}