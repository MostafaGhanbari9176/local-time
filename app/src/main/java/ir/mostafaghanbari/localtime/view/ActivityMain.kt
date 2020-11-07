package ir.mostafaghanbari.localtime.view

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.TransitionSet
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import ir.mostafaghanbari.localtime.R
import ir.mostafaghanbari.localtime.model.models.StateModel
import ir.mostafaghanbari.localtime.view.dialogChoose.DialogChoose
import kotlinx.android.synthetic.main.activity_main.*


class ActivityMain : AppCompatActivity() {

    private val countries = ArrayList<StateModel>().apply {
        add(StateModel(0, "America"))
        add(StateModel(0, "Germany"))
        add(StateModel(0, "Spanish"))
        add(StateModel(0, "France"))
        add(StateModel(0, "Iran"))
    }

    private val cities = ArrayList<StateModel>().apply {
        add(StateModel(1, "America", 0))
        add(StateModel(1, "Germany", 0))
        add(StateModel(1, "Spanish", 0))
        add(StateModel(1, "France", 0))
        add(StateModel(1, "Iran", 0))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startAnimations()

        //setTransition()

        setUpButtons()

    }

    private fun setTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val set = TransitionSet().apply {
                addTransition(ChangeBounds())
                addTransition(Fade())
            }

            TransitionManager.beginDelayedTransition(mainScrollView as ViewGroup, set)
        }
    }

    private fun setUpButtons() {
        btnChooseCountry.setOnClickListener {
            showChooseDialog(countries)
        }

        btnChooseCity.setOnClickListener {
            showChooseDialog(cities)
        }
    }

    private fun showChooseDialog(data: ArrayList<StateModel>) {
        DialogChoose(data, this) { state ->
            Toast.makeText(this@ActivityMain, state.name, Toast.LENGTH_SHORT).show()
            if (state.parentId == -1)
                getCountryCities(state._id)
            else
                getLocalTimeData(state._id)
        }
    }

    private fun getLocalTimeData(_id: Int) {

    }

    private fun getCountryCities(_id: Int) {
        setTransition()
        btnChooseCity.visibility = View.VISIBLE
    }


    private fun startAnimations() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            (mainBack.drawable as AnimatedVectorDrawable).start()
            (mainClock.drawable as AnimatedVectorDrawable).start()
        } else {
            (mainBack.drawable as AnimatedVectorDrawableCompat).start()
            (mainClock.drawable as AnimatedVectorDrawableCompat).start()
        }
    }
}