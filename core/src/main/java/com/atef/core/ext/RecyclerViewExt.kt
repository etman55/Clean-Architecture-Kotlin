package com.atef.core.ext

import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 */

fun RecyclerView.initVerticalRecycler(adapter: RecyclerView.Adapter<*>) {
    this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    this.setHasFixedSize(true)
    this.adapter = adapter
}

fun RecyclerView.initHorizontalRecycler(adapter: RecyclerView.Adapter<*>) {
    this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    this.setHasFixedSize(true)
    this.adapter = adapter
}

fun RecyclerView.addVerticalItemDecoration(@DrawableRes resId: Int) {
    val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
    itemDecoration.setDrawable(context.getSupportDrawable(resId)!!)
    addItemDecoration(itemDecoration)
}

fun RecyclerView.initGridRecycler(adapter: RecyclerView.Adapter<*>, spanCount: Int) {
    this.layoutManager = GridLayoutManager(context, spanCount)
    this.setHasFixedSize(true)
    this.adapter = adapter
}
