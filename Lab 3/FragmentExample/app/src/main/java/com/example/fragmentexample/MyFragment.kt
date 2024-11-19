package com.example.fragmentexample
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
class Fragment1 : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_my, container, false)
        val btnSendData = v.findViewById<Button>(R.id.btn_send_data)
        btnSendData.setOnClickListener {
            val activityFragment1 = activity as MainActivity
            activityFragment1.setResult("Hello from Fragment1")
        }
        return v
    }
}