package com.support.expandedlistviewexample

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.TextView
import android.view.LayoutInflater
import android.graphics.Typeface


/**
 * Created by admin on 3/9/2018.
 */

class ExpandedAdapter(private var _mHeader: ArrayList<String>, private var _mChild: HashMap<String, List<String>>, private var context: Context, private var mselitem: ArrayList<SelModel>) : BaseExpandableListAdapter() {


    override fun getGroupCount(): Int {
        return _mHeader.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return _mChild[_mHeader[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any? {
        return _mHeader[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any? {
        return _mChild[_mHeader[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View? {
        val v: View

        val mExpandableListView = parent as ExpandableListView
        mExpandableListView.expandGroup(groupPosition)

        val listTitle = getGroup(groupPosition) as String
        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            v = layoutInflater.inflate(R.layout.list_group, null)
        } else {
            v = convertView
        }
        val listTitleTextView = v
                .findViewById(R.id.listTitle) as TextView
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = listTitle
        return v
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View? {
        val v: View
        val expandedListText = getChild(groupPosition, childPosition) as String
        if (convertView == null) {
            val layoutInflater = this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            v = layoutInflater.inflate(R.layout.list_item, null)
        } else {
            v = convertView
        }
        val expandedListTextView = v
                .findViewById(R.id.expandedListItem) as TextView
       /* val cb = v
                .findViewById(R.id.cb) as CheckBox

        cb.isChecked = mselitem.contains(expandedListText)*/
        val msel = SelModel()
        msel.item = expandedListText
        msel.parentpos = groupPosition
        msel.childpos = childPosition
        if (StaticData.isPresent(mselitem,msel)) {
            expandedListTextView.setTextColor(context.resources.getColor(android.R.color.holo_red_dark))
        }else{
            expandedListTextView.setTextColor(context.resources.getColor(android.R.color.darker_gray))
        }

        expandedListTextView.text = expandedListText
        return v
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}
