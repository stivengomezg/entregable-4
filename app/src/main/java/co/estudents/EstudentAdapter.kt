package co.estudents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.estudents.base.BaseViewHolder
import co.estudents.data.Estudent
import co.estudents.databinding.ItemEstudentBinding

class EstudentAdapter(private val list: List<Estudent>): RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = ItemEstudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = EstudentViewHolder(itemBinding)

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder) {
            is EstudentViewHolder -> { holder.bind(list[position]) }
        }
    }

    override fun getItemCount(): Int = list.size

    private inner class EstudentViewHolder(
        val binding: ItemEstudentBinding
    ): BaseViewHolder<Estudent>(binding.root) {
        override fun bind(estudent: Estudent) {
            binding.txtName.setText(estudent.name)
            binding.txtLastName.setText(estudent.lastName)
            binding.txtUniversity.setText(estudent.university)
        }

    }
}