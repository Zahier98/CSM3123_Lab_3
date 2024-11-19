package com.example.fragmentexample
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
class Fragment2 : Fragment(), MyInterface {
    private lateinit var tvReceivedData: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_second, container, false)
        tvReceivedData = v.findViewById(R.id.tv_received_data)
        return v
    }
    override fun setResult(data: String) {
        tvReceivedData.setText(data)
        tvReceivedData.setTextColor(Color.BLUE)
    }
}