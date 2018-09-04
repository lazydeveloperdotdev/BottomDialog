package com.rajyadavnp.positive

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.support.design.widget.BottomSheetDialog
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView


class RajDialog constructor(context: Context) {
    private var bottomSheetDialog: BottomSheetDialog? = null
    private var view: LinearLayout? = null

    private var tTitle: TextView? = null
    private var tMessage: TextView? = null
    private var bPositive: Button? = null
    private var bNegative: Button? = null

    init {
        bottomSheetDialog = BottomSheetDialog(context)

        view = LinearLayout(context)
        view!!.orientation = LinearLayout.VERTICAL
        val param = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val paramMargin = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        paramMargin.setMargins(50, 50, 50, 50)
        view!!.layoutParams = paramMargin
        tTitle = TextView(context)
        tTitle!!.isAllCaps = true
        if (Build.VERSION.SDK_INT >= 23) {
            tTitle!!.setTextAppearance(android.R.style.TextAppearance_Large)
        } else {
            tTitle!!.setTextAppearance(context, android.R.style.TextAppearance_Large)
        }
        tTitle!!.setTypeface(null, Typeface.BOLD)
        tTitle!!.layoutParams = param
        tMessage = TextView(context)
        tMessage!!.layoutParams = param

        val buttons = LinearLayout(context)
        buttons.orientation = LinearLayout.HORIZONTAL
        buttons.layoutParams = param
        buttons.gravity = Gravity.RIGHT


        val paramWrap = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        bPositive = Button(context)
        bPositive!!.layoutParams = paramWrap

        bNegative = Button(context)
        bNegative!!.layoutParams = paramWrap

        val typedValue = TypedValue()
        context.theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)

        if (typedValue.resourceId != 0) {
            bPositive!!.setBackgroundResource(typedValue.resourceId)
            bNegative!!.setBackgroundResource(typedValue.resourceId)
        } else {
            bPositive!!.background = null
            bNegative!!.background = null
        }

        bPositive!!.setTextColor(Color.parseColor("#09AE09"))
        bNegative!!.setTextColor(Color.parseColor("#AE0909"))

        buttons.addView(bNegative)
        buttons.addView(bPositive)

        view!!.addView(tTitle)
        view!!.addView(tMessage)
        view!!.addView(buttons)

        tTitle!!.visibility = View.GONE
        tMessage!!.visibility = View.GONE
        bNegative!!.visibility = View.GONE
        bPositive!!.visibility = View.GONE
        bottomSheetDialog!!.setContentView(view!!)
    }

    fun buildAndShow(title: String?, message: String?, button: RajButton?) {
        if (title != null) {
            tTitle!!.visibility = View.VISIBLE
            tTitle!!.text = title
        }

        if (message != null) {
            tMessage!!.visibility = View.VISIBLE
            tMessage!!.text = message
        }

        if (button != null) {
            bPositive!!.visibility = View.VISIBLE
            bPositive!!.text = button.text
            bPositive!!.setTextColor(Color.BLACK)
            bPositive!!.setOnClickListener {
                bottomSheetDialog!!.dismiss()
                try {
                    button.listener!!.onClick(dialogUI())
                } catch (e: Exception) {
                    Log.e("CLICK", "No action")
                }
            }
        }
        bottomSheetDialog!!.show()
    }


    fun buildAndShow(title: String?, message: String?, positive: RajButton?, negative: RajButton?) {
        if (title != null) {
            tTitle!!.visibility = View.VISIBLE
            tTitle!!.text = title
        }

        if (message != null) {
            tMessage!!.visibility = View.VISIBLE
            tMessage!!.text = message
        }

        if (positive != null) {
            bPositive!!.visibility = View.VISIBLE
            bPositive!!.text = positive.text
            bPositive!!.setOnClickListener {
                bottomSheetDialog!!.dismiss()
                try {
                    positive.listener!!.onClick(dialogUI())
                } catch (e: Exception) {
                    Log.e("CLICK", "No action")
                }
            }
        }
        if (negative != null) {
            bNegative!!.visibility = View.VISIBLE
            bNegative!!.text = negative.text
            bNegative!!.setOnClickListener {
                bottomSheetDialog!!.dismiss()
                try {
                    negative.listener!!.onClick(dialogUI())
                } catch (e: Exception) {
                    Log.e("CLICK", "No action")
                }
            }
        }
        bottomSheetDialog!!.show()
    }

    fun setTitle(title: String?): RajDialog {
        if (title != null) {
            tTitle!!.visibility = View.VISIBLE
            tTitle!!.text = title
        }
        return this
    }

    fun setMessage(message: String?): RajDialog {
        if (message != null) {
            tMessage!!.visibility = View.VISIBLE
            tMessage!!.text = message
        }
        return this
    }

    fun setCancelable(yesNo: Boolean): RajDialog {
        bottomSheetDialog!!.setCancelable(yesNo)
        return this
    }

    fun setCancelableOnOutsideTouch(yesNo: Boolean): RajDialog {
        bottomSheetDialog!!.setCanceledOnTouchOutside(yesNo)
        return this
    }

    fun show() {
        bottomSheetDialog!!.show()
    }

    fun setPositiveButton(button: RajButton): RajDialog {
        bPositive!!.visibility = View.VISIBLE
        bPositive!!.text = button.text

        bPositive!!.setOnClickListener {
            try {
                button.listener!!.onClick(dialogUI())
            } catch (e: Exception) {
                Log.e("CLICK", "No action")
            }
        }
        return this
    }

    inner class dialogUI {
        fun dismiss() {
            bottomSheetDialog!!.dismiss()
        }

        fun cancel() {
            bottomSheetDialog!!.dismiss()
        }
    }

    fun setNegativeButton(button: RajButton): RajDialog {
        bNegative!!.visibility = View.VISIBLE
        bNegative!!.text = button.text

        bNegative!!.setOnClickListener {
            try {
                button.listener!!.onClick(dialogUI())
            } catch (e: Exception) {
                Log.e("CLICK", "No action")
            }
        }
        return this
    }


    companion object {
        class RajButton(text: String, listener: ClickListener) {
            var text: String? = text
            var listener: ClickListener? = listener
        }

        interface ClickListener {
            fun onClick(dialog: dialogUI)
        }
    }
}