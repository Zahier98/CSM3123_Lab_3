package com.example.fragmentexample
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity(), MyInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment1 = Fragment1()
        val fragment2 = Fragment2()
        supportFragmentManager.beginTransaction().add(R.id.fl_fragment1, fragment1,
            "Fragment1").commit()
        supportFragmentManager.beginTransaction().add(R.id.fl_fragment2, fragment2,
            "Fragment2").commit()
    }
    override fun setResult(data: String) {
        val fragment2 = supportFragmentManager.findFragmentByTag("Fragment2") as
                Fragment2
        fragment2.setResult(data)
    }
}