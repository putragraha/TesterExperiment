package com.android.nsystem.testapps.fruit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.nsystem.testapps.R
import com.android.nsystem.testapps.databinding.ItemFruitBinding

/**
 * @author Putra Nugraha (putra.nugraha@dana.id)
 * @version FruitAdapter, v 0.1 17/12/20 12.31 by Putra Nugraha
 */
class FruitAdapter: ListAdapter<Fruit, FruitAdapter.FruitViewHolder>(DIFF_CALLBACK) {

    companion object {

        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Fruit> = object :
            DiffUtil.ItemCallback<Fruit>() {

            override fun areItemsTheSame(oldItem: Fruit, newItem: Fruit): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Fruit, newItem: Fruit): Boolean {
                return oldItem.name == newItem.name && oldItem.image == newItem.image
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        return FruitViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_fruit, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FruitViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val binding = ItemFruitBinding.bind(itemView)

        fun bind(fruit: Fruit) = binding.actvItem.run {
            text = fruit.name
            setCompoundDrawablesWithIntrinsicBounds(fruit.image, 0, 0, 0)
        }
    }
}