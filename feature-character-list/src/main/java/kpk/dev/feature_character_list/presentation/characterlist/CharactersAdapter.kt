package kpk.dev.feature_character_list.presentation.characterlist

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_character.view.*
import kpk.dev.feature_character_list.R
import kpk.dev.feature_character_list.presentation.model.CharacterItem
import kpk.dev.presentation.inflate
import kpk.dev.presentation.loadImage

class CharactersAdapter(private val listener: (Int) -> Unit) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    private val charList = mutableListOf<CharacterItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(parent.inflate(R.layout.item_character))

    override fun getItemCount(): Int = charList.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(charList[position], listener)
    }

    fun updateData(list: List<CharacterItem>?) {
        if (charList.isNotEmpty()) {
            charList.clear()
        }
        list?.let {
            charList.addAll(it)
        }
        notifyDataSetChanged()
    }

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(character: CharacterItem, listener: (Int) -> Unit) = with(itemView) {
            tv_char_name.text = character.name
            iv_char_img.loadImage(character.img)
            setOnClickListener { listener(character.charId) }
        }
    }
}