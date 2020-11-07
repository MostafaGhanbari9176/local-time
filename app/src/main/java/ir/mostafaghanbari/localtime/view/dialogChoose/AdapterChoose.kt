package ir.mostafaghanbari.localtime.view.dialogChoose

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.mostafaghanbari.localtime.R
import ir.mostafaghanbari.localtime.model.models.StateModel
import kotlinx.android.synthetic.main.item_choose.view.*

class AdapterChoose(
    private val data: ArrayList<StateModel>,
    private val ctx: Context,
    private val listener:(state:StateModel) -> Unit
) : RecyclerView.Adapter<AdapterChoose.MyHolder>() {

    class MyHolder(v: View) : RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder =
        MyHolder(LayoutInflater.from(ctx).inflate(R.layout.item_choose, null, false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.itemView.apply {
            rbChoose.text = data[position].name
            rbChoose.setOnClickListener {
                listener(data[position])
            }
        }
    }

}