# BottomDialog
Android library to display AlertDialog from the bottom

**Usage**
```
BottomDialog(this).buildAndShow("Logout?", "Do you really want to logout?", 
            BottomButton("Yes", object : BottomDialog.Companion.ClickListener {
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
