// To prevent bugs caused by the filter functionality in ArrayAdapter,
// we need to create our own implementation.

package com.example.receiptpocket.pocket.win

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter

class NoFilterAdapter(context: Context, resource: Int, val items: Array<String>
    ): ArrayAdapter<String>(context, resource, items) {

    override fun getFilter(): Filter {
        return DisabledFilter()
    }

    private inner class DisabledFilter: Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val result = FilterResults()
            result.values = items
            result.count = items.size
            return result
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            notifyDataSetChanged()
        }
    }
}