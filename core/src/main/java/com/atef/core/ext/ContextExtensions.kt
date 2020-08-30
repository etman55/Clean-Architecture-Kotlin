package com.atef.core.ext

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.TypedValue
import android.widget.Toast
import androidx.annotation.ArrayRes
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.Dimension
import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.core.content.ContextCompat

@ColorInt
fun Context.getSupportColor(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}

fun Context.getSupportDrawable(@DrawableRes resId: Int): Drawable? {
    return ContextCompat.getDrawable(this, resId)
}

fun Context.getInteger(@IntegerRes integerRes: Int): Int {
    return resources.getInteger(integerRes)
}

fun Context.getStringArray(@ArrayRes arrayRes: Int): Array<String> {
    return this.resources.getStringArray(arrayRes)
}

fun Context.resolveAttribute(@AttrRes idRes: Int, resolveRefs: Boolean = false): TypedValue {
    val typedValue = TypedValue()
    theme.resolveAttribute(idRes, typedValue, resolveRefs)
    return typedValue
}

fun Context.getAttrValueResId(@AttrRes idRes: Int): Int {
    return resolveAttribute(idRes, true).resourceId
}

fun Context?.startSupportActionMode(actionModeCallback: ActionMode.Callback): ActionMode? {
    this ?: return null
    if (this is AppCompatActivity) return this.startSupportActionMode(actionModeCallback)
    return null
}

fun Context?.startActivitySafe(intent: Intent) {
    this ?: return
    if (intent.resolveActivity(packageManager) != null) startActivity(intent)
}

fun Context?.dialNumber(number: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$number")
    }
    startActivitySafe(intent)
}

fun Context?.openSmsWithNumber(number: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("sms:$number")
    }
    startActivitySafe(intent)
}

fun Context.getDimension(@DimenRes dimen: Int) = resources.getDimension(dimen)

fun Context.getDimensionInt(@DimenRes dimen: Int) = getDimension(dimen).toInt()

fun Context.getDimensionPixelSize(@DimenRes dimen: Int): Int {
    return this.resources.getDimensionPixelSize(dimen)
}

@Dimension
fun Context.getAttributePixelSize(@AttrRes attrRes: Int): Int {
    return this.getDimensionPixelSize(this.getAttrValueResId(attrRes))
}

@ColorInt
fun Context.getAttributeColorInt(@AttrRes attrRes: Int): Int {
    return this.getSupportColor(this.getAttrValueResId(attrRes))
}

fun Context.checkSelfPermissionCompat(permission: String): Int {
    return ContextCompat.checkSelfPermission(this, permission)
}

fun Context.isPermissionGranted(permission: String): Boolean {
    return checkSelfPermissionCompat(permission) == PackageManager.PERMISSION_GRANTED
}

/**
 * Toast/snackbar related extensions
 */
fun Context?.toastShort(msg: String) {
    this?.let { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show() }
}

fun Context?.toastLong(msg: String) {
    this?.let { Toast.makeText(this, msg, Toast.LENGTH_LONG).show() }
}

fun Context?.toastShort(@StringRes msg: Int) {
    this?.let { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show() }
}

fun Context?.toastLong(@StringRes msg: Int) {
    this?.let { Toast.makeText(this, msg, Toast.LENGTH_LONG).show() }
}
