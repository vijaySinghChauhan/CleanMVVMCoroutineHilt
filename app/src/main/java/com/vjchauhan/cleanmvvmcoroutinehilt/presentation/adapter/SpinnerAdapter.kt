package com.vjchauhan.cleanmvvmcoroutinehilt.presentation.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.vjchauhan.cleanmvvmcoroutinehilt.R
import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.spinner.Data
import com.vjchauhan.cleanmvvmcoroutinehilt.presentation.view.Spinner
import java.util.*


class AutocompleteCustomArrayAdapter(
    var mContext: Context,
    var layoutResourceId: Int,
    data: List<Data>?
) :
    ArrayAdapter<Data?>(mContext, layoutResourceId, data!!) {
    val TAG = "AutocompleteCustomArrayAdapter.java"
    var data: List<Data>? = null
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        try {

            /*
             * The convertView argument is essentially a "ScrapView" as described is Lucas post
             * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
             * It will have a non-null value when ListView is asking you recycle the row layout.
             * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
             */
            if (convertView == null) {
                // inflate the layout
                val inflater: LayoutInflater = (mContext as Spinner).getLayoutInflater()
                convertView = inflater.inflate(layoutResourceId, parent, false)
            }

            // object item based on the position
            val objectItem: Data = data!![position]

            // get the TextView and then set the text (item name) and tag (item ID) values
            val textViewItem = convertView!!.findViewById<View>(R.id.textView) as TextView
            textViewItem.setText(objectItem.country)

            // in case you want to add some style, you can do something like:
            textViewItem.setBackgroundColor(Color.CYAN)
        } catch (e: NullPointerException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return convertView!!
    }

    init {
        this.data = data
    }
}