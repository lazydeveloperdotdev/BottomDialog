# BottomDialog
Android library to display AlertDialog from the bottom

<h2>Usage</h2>

**1. Prompt Dialog**
```
BottomDialog(this).buildAndShow("Title", "Your message", 
            BottomButton("Yes", object : ClickListener {
                override fun onClick(dialog: BottomDialog.dialogUI) {
                    //do something
                }
            }), 
            BottomButton("No", object : ClickListener {
                override fun onClick(dialog: BottomDialog.dialogUI) {
                    dialog.dismiss()
                }
            }))
```

**2. Alert Dialog**
```
BottomDialog(this).buildAndShow("Title", "Your message", 
            BottomButton("OK", object : ClickListener {
                override fun onClick(dialog: BottomDialog.dialogUI) {
                    //do something
                }
            })
```

**3. Customizations:**
- If you don't want the dialog to be dismissed automatically, pass one more ```Boolean``` parameter as ```false```
- Methods: 
<br />```setCancelable(Boolean)``` - dialog gets cancelled on back pressed
<br />```setCancelableOnOutsideTouch(Boolean)``` - dialog gets cancelled if touched outside the dialog
