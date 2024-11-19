package com.example.sqlitedemo
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper = DatabaseHelper(this)
        val addUserButton = findViewById<Button>(R.id.btn_add)
        val displayUsersButton = findViewById<Button>(R.id.btn_view)
        val updateUserButton = findViewById<Button>(R.id.btn_update)
        val deleteUserButton = findViewById<Button>(R.id.btn_delete)
        val filterUsersButton = findViewById<Button>(R.id.btn_filter)
        val nameEditText = findViewById<EditText>(R.id.et_name)
        val ageEditText = findViewById<EditText>(R.id.et_age)
        val idEditText = findViewById<EditText>(R.id.et_id)
        val displayTextView = findViewById<TextView>(R.id.tv_result)
        // Add User
        addUserButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val age = ageEditText.text.toString().toIntOrNull()
            if (!name.isBlank() && age != null) {
                dbHelper.addUser(name, age)
                nameEditText.text.clear()
                ageEditText.text.clear()
            }
        }
        // Display Users
        displayUsersButton.setOnClickListener {
            val users = dbHelper.getAllUsers()
            displayTextView.text = users.joinToString("\n")
        }
        // Update User
        updateUserButton.setOnClickListener {
            val id = idEditText.text.toString().toIntOrNull()
            val name = nameEditText.text.toString()
            val age = ageEditText.text.toString().toIntOrNull()
            if (id != null && !name.isBlank() && age != null) {
                if (dbHelper.updateUser(id, name, age)) {
                    displayTextView.text = "User Updated"
                } else {
                    displayTextView.text = "Failed to update user"
                }
            }
        }
        // Delete User
        deleteUserButton.setOnClickListener {
            val id = idEditText.text.toString().toIntOrNull()
            if (id != null) {
                if (dbHelper.deleteUser(id)) {
                    displayTextView.text = "User Deleted"
                } else {
                    displayTextView.text = "Failed to delete user"
                }
            }
        }
        // Filter Users by Age
        filterUsersButton.setOnClickListener {
            val minAge = ageEditText.text.toString().toIntOrNull()
            if (minAge != null) {
                val filteredUsers = dbHelper.getUsersByAge(minAge)
                displayTextView.text = filteredUsers.joinToString("\n")
            }
        }
    }
}