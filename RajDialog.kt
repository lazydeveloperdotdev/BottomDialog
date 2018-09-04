package com.rajyadavnp.positive

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.support.design.widget.BottomSheetDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView

class RajDialog constructor(context: Context) {
    private var bottomSheetDialog: Dialog? = null
    private var view: View? = null

    private var tTitle: TextView? = null
    private var tMessage: TextView? = null
    private var bPositive: Button? = null
    private var bNegative: Button? = null

    init {
        bottomSheetDialog = BottomSheetDialog(context)
        view = LayoutInflater.from(context).inflate(R.layout.dialog_sheet, null, false)
        tTitle = view!!.findViewById(R.id.title)
        tMessage = view!!.findViewById(R.id.message)
        bPositive = view!!.findViewById(R.id.positive)
        bNegative = view!!.findViewById(R.id.negative)

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