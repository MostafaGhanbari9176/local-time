package ir.mostafaghanbari.localtime.view.dialogChoose

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import ir.mostafaghanbari.localtime.R
import ir.mostafaghanbari.localtime.models.StateModel
import kotlinx.android.synthetic.main.dialog_choose.view.*

class DialogChoose(
    data: List<StateModel>,
    ctx: Context,
    listener: (state: StateModel) -> Unit
) : Dialog(ctx) {

    private val v = LayoutInflater.from(ctx).inflate(R.layout.dialog_choose, null, false)

    init {
        v.RVChoose.apply {
            adapter = AdapterChoose(data, ctx) {
                listener(it)
                cancel()
            }
            layoutManager = LinearLayoutManager(ctx)
        }
        v.closeChooseDialog.setOnClickListener {
            cancel()
        }
        setContentView(v)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        show()
    }

}