package com.support.expandedlistviewexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ExpandableListView
import java.io.IOException
import com.google.gson.Gson



class MainActivity : AppCompatActivity(), ExpandableListView.OnChildClickListener {


    private lateinit var elv: ExpandableListView
    private lateinit var mAdapter: ExpandedAdapter
    private lateinit  var mHeader:ArrayList<String>
    private var mselitem:ArrayList<String> = ArrayList()
    private  var mchild:HashMap<String,List<String>> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        populateData(loadJSONFromAsset())

        elv = findViewById(R.id.elv)

        elv.setOnChildClickListener(this)
        mAdapter = ExpandedAdapter(mHeader,mchild,this,mselitem)
        elv.setAdapter(mAdapter)
    }

    private fun setAdapter() {
        mAdapter.notifyDataSetChanged()
    }


    override fun onChildClick(p0: ExpandableListView?, v: View?, groupPosition: Int, childPosition: Int, id: Long): Boolean {
        val s = mchild[mHeader[groupPosition]]!![childPosition]
        if (mselitem.contains(s)){
            mselitem.remove(s)
        }else {
            mselitem.add(s)
        }
        Log.d("ssltag",mchild[mHeader[groupPosition]]!![childPosition])
        setAdapter()
        return true
    }

    private fun populateData(loadJSONFromAsset: String?) {
        val gson = Gson()
        val student = gson.fromJson(loadJSONFromAsset, JsonModel::class.java)
        Log.d("ssltag","$student one = ${student.one!!.size} two = ${student.two!!.size} three = ${student.three!!.size} four = ${student.four!!.size} five = ${student.five!!.size}")
        if (!student.one!!.isEmpty()){
            mchild["one"] = student.one!!
            Log.d("ssltag", "one = ${student.one}")
        }
        if (!student.two!!.isEmpty()){
            mchild["two"] = student.two!!
            Log.d("ssltag", "two = ${student.two} ")
        }
        if (!student.three!!.isEmpty()){
            mchild["three"] = student.three!!
            Log.d("ssltag", "three = ${student.three}")
        }
        if (!student.four!!.isEmpty()){
            mchild["four"] = student.four!!
            Log.d("ssltag", "four = ${student.four}")
        }
        if (!student.five!!.isEmpty()){
            mchild["five"] = student.five!!
            Log.d("ssltag", "five = ${student.five}")
        }
        mHeader = ArrayList(mchild.keys)
        Log.d("ssltag", "keys = $mHeader")
        Log.d("ssltag", "five = $mchild")
    }

    private fun loadJSONFromAsset(): String? {
        var json: String? = null
        try {
            val `is` = this.assets.open("sample.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            Log.d("ssltag",ex.toString())
            return null
        }
        Log.d("ssltag",json)
        return json
    }


}
