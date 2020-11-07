package ir.mostafaghanbari.localtime.view

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.TransitionSet
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import ir.mostafaghanbari.localtime.R
import ir.mostafaghanbari.localtime.controllers.States
import ir.mostafaghanbari.localtime.models.StateModel
import ir.mostafaghanbari.localtime.view.dialogChoose.DialogChoose
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class ActivityMain : AppCompatActivity() {

    private lateinit var selectedCountry: StateModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startAnimations()

        setUpButtons()

    }

    private fun setTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val set = TransitionSet().apply {
                addTransition(ChangeBounds())
                addTransition(Fade())
            }

            TransitionManager.beginDelayedTransition(mainView as ViewGroup, set)
        }
    }

    private fun setUpButtons() {
        btnChooseCountry.setOnClickListener {
            getCountries()
        }

        btnChooseCity.setOnClickListener {
            getCountryCities()
        }
    }

    private fun getCountries() {
        States(object : States.StateListener {
            override fun result(data: List<StateModel>) {
                showChooseDialog(data)
            }
        }).getCountries()
    }

    private fun showChooseDialog(data: List<StateModel>) {
        DialogChoose(data, this) { state ->
            setTransition()
            if (state.parentId == -1) {
                selectedCountry = state
                resetViews(state)
            } else
                setUpLocalTimeData(state)
        }
    }

    private fun resetViews(state: StateModel) {
        btnChooseCountry.text = state.name

        btnChooseCity.apply {
            visibility = View.VISIBLE
            text = "Choose City"
        }

        RVClock.visibility = View.GONE

    }

    private fun setUpLocalTimeData(city: StateModel) {
        btnChooseCity.text = city.name
        RVClock.visibility = View.VISIBLE

        val timeZone = TimeZone.getTimeZone(city.timeZone)
        val calendar = Calendar.getInstance(timeZone)

        val simpleDateFormat = SimpleDateFormat("hh:mm", Locale.US)
        simpleDateFormat.timeZone = timeZone

        txtClock.text = simpleDateFormat.format(calendar.time)
        txtPM_AM.text = if (calendar.get(Calendar.AM_PM) == Calendar.AM) "AM" else "PM"

        txtStateName.text = city.name
    }

    private fun getCountryCities() {
        States(object : States.StateListener {
            override fun result(data: List<StateModel>) {
                showChooseDialog(data)
            }
        }).getCities(selectedCountry)
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