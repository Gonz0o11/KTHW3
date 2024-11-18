package com.example.kthw3_1

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    data class Data(
        val photo: Int,
        val name: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 交通工具資料
        val transNameArray = arrayOf("腳踏車", "機車", "汽車", "巴士", "飛機", "輪船")
        val transPhotoIdArray = intArrayOf(
            R.drawable.trans1, R.drawable.trans2, R.drawable.trans3,
            R.drawable.trans4, R.drawable.trans5, R.drawable.trans6
        )

        val transData = Array(transNameArray.size) { i ->
            Data(transPhotoIdArray[i], transNameArray[i])
        }

        val transAdapter = MyAdapter(transData, R.layout.trans_list)
        val spinner: Spinner = findViewById(R.id.spinner)
        spinner.adapter = transAdapter

        // Cubee 資料
        val cubeeNameArray = arrayOf(
            "哭哭", "發抖", "再見", "生氣", "昏倒", "竊笑",
            "很棒", "你好", "驚嚇", "大笑"
        )
        val cubeePhotoIdArray = intArrayOf(
            R.drawable.cubee1, R.drawable.cubee2, R.drawable.cubee3,
            R.drawable.cubee4, R.drawable.cubee5, R.drawable.cubee6,
            R.drawable.cubee7, R.drawable.cubee8, R.drawable.cubee9, R.drawable.cubee10
        )

        val cubeeData = Array(cubeeNameArray.size) { i ->
            Data(cubeePhotoIdArray[i], cubeeNameArray[i])
        }

        val cubeeAdapter = MyAdapter(cubeeData, R.layout.cubee_list)
        val gridView: GridView = findViewById(R.id.gridView)
        gridView.adapter = cubeeAdapter
        gridView.numColumns = 3

        // 訊息列表資料
        val messageArray = arrayOf("訊息1", "訊息2", "訊息3", "訊息4", "訊息5", "訊息6")

        val messageAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            messageArray
        )

        val listView: ListView = findViewById(R.id.listView)
        listView.adapter = messageAdapter
    }
}

class MyAdapter(
    private val data: Array<MainActivity.Data>,
    private val view: Int
) : BaseAdapter() {

    override fun getCount(): Int = data.size

    override fun getItem(position: Int): Any = data[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView = convertView ?: View.inflate(parent?.context, view, null)
        val name = itemView.findViewById<TextView>(R.id.name)
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)

        name.text = data[position].name
        imageView.setImageResource(data[position].photo)

        return itemView
    }
}
